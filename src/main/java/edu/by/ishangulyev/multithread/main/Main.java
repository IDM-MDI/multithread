package edu.by.ishangulyev.multithread.main;

import edu.by.ishangulyev.multithread.entity.Customer;
import edu.by.ishangulyev.multithread.entity.CustomerState;
import edu.by.ishangulyev.multithread.entity.FastFood;
import edu.by.ishangulyev.multithread.reader.CustomerReaderBuilder;
import edu.by.ishangulyev.multithread.reader.impl.CustomerReaderBuilderImpl;

import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        LinkedList<Customer> customers = new LinkedList<>();
        List<Customer> customers1 = new ArrayList<>();
        FastFood mcDonalds = FastFood.getInstance();
//
//        for (int i = 0; i < 10; i++)
//        {
//            customers.add(new Customer("Customer: "+i,i+20, CustomerState.MAKE_ORDER));
//            customers1.add(new Customer("Customer: "+i,i+20, CustomerState.ONLINE));
//        }
//
//        mcDonalds.setOnlineOrder(customers1);
//        mcDonalds.addQueue(customers);

//        mcDonalds.startThread();

        CustomerReaderBuilder readerBuilder = new CustomerReaderBuilderImpl();

        String path = "src/main/resources/files/property.properties";
        List<Customer> customers2 = readerBuilder.setPath(path).read().getResult();
        System.out.println(customers2);
    }
}
