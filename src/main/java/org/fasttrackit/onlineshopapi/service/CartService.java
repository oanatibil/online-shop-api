package org.fasttrackit.onlineshopapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.onlineshopapi.domain.Cart;
import org.fasttrackit.onlineshopapi.domain.Customer;
import org.fasttrackit.onlineshopapi.domain.Product;
import org.fasttrackit.onlineshopapi.persistence.CartRepository;
import org.fasttrackit.onlineshopapi.transfer.product.cart.SaveCartRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(CartService.class);

    private final CartRepository cartRepository;
    private final CustomerService customerService;
    private final ProductService productService;
    private final ObjectMapper objectMapper;

    @Autowired
    public CartService(CartRepository cartRepository, CustomerService customerService, ProductService productService, ObjectMapper objectMapper) {
        this.cartRepository = cartRepository;
        this.customerService = customerService;
        this.productService = productService;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public Cart addProductsToCart(SaveCartRequest request) throws Exception {
        LOGGER.info("Adding products to cart: {}", request);
        Customer customer = customerService.getCustomer(request.getCustomerId());

        Cart cart = new Cart();
        cart.setCustomer(customer);

        for (Long id : request.getProductIds()){
            //could be done more efficiently with a getAllProductsByIds
            Product product = productService.getProduct(id);
            cart.addProduct(product);
        }

        return cartRepository.save(cart);
    }

}
