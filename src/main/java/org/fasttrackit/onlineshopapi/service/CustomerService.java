package org.fasttrackit.onlineshopapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.onlineshopapi.domain.Customer;
import org.fasttrackit.onlineshopapi.domain.Product;
import org.fasttrackit.onlineshopapi.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshopapi.persistence.CustomerRepository;
import org.fasttrackit.onlineshopapi.transfer.product.UpdateProductRequest;
import org.fasttrackit.onlineshopapi.transfer.product.customer.CreateCustomerRequest;
import org.fasttrackit.onlineshopapi.transfer.product.customer.UpdateCostumerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


    @Service
    public class CustomerService {

        private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);
        private final CustomerRepository customerRepository;
        private final ObjectMapper objectMapper;

        public CustomerService(CustomerRepository customerRepository, ObjectMapper objectMapper) {
            this.customerRepository = customerRepository;
            this.objectMapper = objectMapper;
        }

        public Customer createCustomer(CreateCustomerRequest request) {
            LOGGER.info("Creating Customer {}", request);
            Customer customer = objectMapper.convertValue(request, Customer.class);
            return customerRepository.save(customer);
        }

        public Customer getCustomer(long id) throws Exception {

            LOGGER.info("Retrieving product {}", id);
            return customerRepository.findById(id)
                    // lambda expression
                    .orElseThrow(()-> new ResourceNotFoundException("Customer" + id + "not found"));

        }

        public Customer updateCustomer(long id, UpdateCostumerRequest request) throws Exception {

            LOGGER.info ("Updating product{}, {}", id, request);

            Customer customer = getCustomer(id);

            BeanUtils.copyProperties(request, customer);

            return customerRepository.save(customer);

        }

        public void deleteCustomer (long id){
            LOGGER.info("Deleting customer {}", id);
            customerRepository.deleteById(id);
            LOGGER.info("Deleted product {}", id);
        }


}
