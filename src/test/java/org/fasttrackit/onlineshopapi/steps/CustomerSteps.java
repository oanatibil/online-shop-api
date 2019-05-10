package org.fasttrackit.onlineshopapi.steps;

import org.fasttrackit.onlineshopapi.domain.Customer;
import org.fasttrackit.onlineshopapi.service.CustomerService;
import org.fasttrackit.onlineshopapi.transfer.product.customer.CreateCustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerSteps {

    @Autowired
    private CustomerService customerService;

    public Customer createCustomer() {
        CreateCustomerRequest customer = new CreateCustomerRequest();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setAddress("test address");
        customer.setEmail("john@example.com");
        customer.setPhone("0432313");
        customer.setUsername("johnny");
        customer.setPassword("pass");

        return customerService.createCustomer(customer);
    }
}
