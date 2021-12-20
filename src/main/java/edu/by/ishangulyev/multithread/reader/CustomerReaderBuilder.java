package edu.by.ishangulyev.multithread.reader;

import edu.by.ishangulyev.multithread.entity.Customer;

import java.util.List;

public interface CustomerReaderBuilder
{
    CustomerReaderBuilder setPath(String path);
    CustomerReaderBuilder read();
    List<Customer> getResult();
}
