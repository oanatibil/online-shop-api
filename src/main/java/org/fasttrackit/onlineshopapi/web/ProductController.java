package org.fasttrackit.onlineshopapi.web;

import org.fasttrackit.onlineshopapi.domain.Product;
import org.fasttrackit.onlineshopapi.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshopapi.service.ProductService;
import org.fasttrackit.onlineshopapi.transfer.product.CreateProductRequest;
import org.fasttrackit.onlineshopapi.transfer.product.GetProductsRequest;
import org.fasttrackit.onlineshopapi.transfer.product.UpdateProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //@RequestMapping(method = RequestMethod.GET, path = "/{id}")
    //same as the annotaton above
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") long id) throws Exception {
        Product response = productService.getProduct(id);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid CreateProductRequest request){
        Product response = productService.createProduct(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable long id, @RequestBody @Valid UpdateProductRequest request) throws Exception {

        productService.updateProduct(id, request);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteproduct(@PathVariable long id) throws ResourceNotFoundException {

        productService.deleteProduct(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getProducts(@Valid GetProductsRequest request, Pageable pageable){
        Page<Product> response = productService.getProducts(request, pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
