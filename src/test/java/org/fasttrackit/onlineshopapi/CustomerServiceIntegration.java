package org.fasttrackit.onlineshopapi;

import org.fasttrackit.onlineshopapi.domain.Customer;
import org.fasttrackit.onlineshopapi.domain.Product;
import org.fasttrackit.onlineshopapi.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshopapi.service.CustomerService;
import org.fasttrackit.onlineshopapi.transfer.product.UpdateProductRequest;
import org.fasttrackit.onlineshopapi.transfer.product.customer.CreateCustomerRequest;
import org.fasttrackit.onlineshopapi.transfer.product.customer.UpdateCostumerRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

public class CustomerServiceIntegration {

    @RunWith(SpringRunner.class)
    @SpringBootTest
    public class CustomerServiceIntegrationTests {

        @Autowired
        private CustomerService customerService;

        @Test
        public void testCreateCustomer_whenValidRequest_thenReturnProductWithId() {
            Customer customer = createCustomer();

            assertThat(customer, notNullValue());
            assertThat(customer.getId(), greaterThan(0L));
        }

        private Customer createCustomer() {
            CreateCustomerRequest request = new CreateCustomerRequest();
            request.setFirstName("Oana");

            /* private String lastName;

            private String address;

            private String email;
            private String phone;

            private String username;
            private String password; */

            return customerService.createCustomer(request);
        }

        @Test (expected = ResourceNotFoundException.class)
        public void testGetProduct_whenProductNotFound_thenThrowResourceNotFoundException() throws Exception {
            customerService.getCustomer(0);
        }

        @Test
        public void testGetCustomer_whenExistingID_thenReturnMatchingCustomer() throws Exception {

            Customer customer = createCustomer();
            Customer retrievedCustomer = customerService.getCustomer(customer.getId());

            assertThat(retrievedCustomer.getId(), is(customer.getId()));
            assertThat(retrievedCustomer.getFirstName(), is(customer.getFirstName()));

        }
/*
        @Test

        public void testUpdateProduct_whenValidRequestWithAllFields_thenReturnUpdatedProduct () throws Exception {
            Customer createdCustomer = createCustomer();

            UpdateProductRequest request = new UpdateProductRequest();
            request.setName(createdProduct.getName() + "_Edited");
            request.setPrice(createdProduct.getPrice() + 10);
            request.setQuantity(createdProduct.getQuantity() + 10);
            request.setSku(createdProduct.getSku() + "_Edited");
            Product updatedProduct = productService.updateProduct(createdProduct.getId(), request);

            assertThat(updatedProduct.getName(), is(request.getName()));
            assertThat(updatedProduct.getName(), not(is(createdProduct.getName())));
            assertThat(updatedProduct.getPrice(), is(request.getPrice()));
            assertThat(updatedProduct.getQuantity(), is(request.getQuantity()));
            assertThat(updatedProduct.getSku(), is(request.getSku()));
            assertThat(updatedProduct.getId(), is(createdProduct.getId()));
        } */

        //todo: Implement negative tests for update and tests fpr update with some fields only

        @Test(expected = ResourceNotFoundException.class)

        public void testDeleteCustomer_whenExistingID_thenCustomerIsDeleted () throws Exception {
            Customer createdCustomer = createCustomer();
            customerService.deleteCustomer(createdCustomer.getId());
            customerService.getCustomer(createdCustomer.getId());
        }

    }


}
