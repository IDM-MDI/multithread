package edu.by.ishangulyev.multithread.builder;

import edu.by.ishangulyev.multithread.entity.Customer;
import edu.by.ishangulyev.multithread.entity.CustomerState;

public interface CustomerBuilder
{
    CustomerBuilder setName(String name);
    CustomerBuilder setAge(int age);
    CustomerBuilder setCustomerState(CustomerState state);
    Customer build();
}
