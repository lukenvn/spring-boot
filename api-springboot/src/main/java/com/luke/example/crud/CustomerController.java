package com.luke.example.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by nvnhung on 06/01/2017.
 */
@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public CustomerEntity findOne(@RequestParam(name = "id") long id) {
        CustomerEntity customerEntity = customerRepository.findOne(id);
        return customerEntity;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public List<CustomerEntity> findAll() {
        return customerRepository.findAll();
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public CustomerEntity create(@RequestBody Customer customer) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setFirstName(customer.getFirstName());
        customerEntity.setLastName(customer.getLastName());
        return customerRepository.save(customerEntity);

    }

}
