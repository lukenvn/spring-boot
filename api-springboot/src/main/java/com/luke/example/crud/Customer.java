package com.luke.example.crud;

/**
 * Created by nvnhung on 06/01/2017.
 */
public class Customer {

    private String firstName;
    private String lastName;


    public String getFirstName() {
        return firstName;
    }

    public Customer setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Customer setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
