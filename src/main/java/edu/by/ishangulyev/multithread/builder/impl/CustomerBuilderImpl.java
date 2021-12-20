package edu.by.ishangulyev.multithread.builder.impl;

import edu.by.ishangulyev.multithread.builder.CustomerBuilder;
import edu.by.ishangulyev.multithread.entity.Customer;
import edu.by.ishangulyev.multithread.entity.CustomerState;

public class CustomerBuilderImpl implements CustomerBuilder
{
    private CustomerState state;
    private String name;
    private int age;

    @Override
    public CustomerBuilder setName(String name)
    {
        this.name = name;
        return this;
    }

    @Override
    public CustomerBuilder setAge(int age)
    {
        this.age = age;
        return this;
    }

    @Override
    public CustomerBuilder setCustomerState(CustomerState state)
    {
        this.state = state;
        return this;
    }

    @Override
    public Customer build()
    {
        return new Customer(name,age,state);
    }
}
