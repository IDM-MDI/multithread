package edu.by.ishangulyev.multithread.main;

import edu.by.ishangulyev.multithread.entity.Customer;
import edu.by.ishangulyev.multithread.entity.CustomerState;
import edu.by.ishangulyev.multithread.entity.FastFood;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main
{
    public static void main(String[] args)
    {
        Queue<Customer> customers = new ArrayDeque<>();
        List<Customer> customers1 = new ArrayList<>();
        FastFood mcDonalds = FastFood.getInstance();

        for (int i = 0; i < 10; i++)
        {
            customers.add(new Customer("Customer: "+i,i+20, CustomerState.MAKE_ORDER));
            customers1.add(new Customer("Customer: "+i,i+20, CustomerState.ONLINE));
        }

        mcDonalds.setOnlineOrder(customers1);
        mcDonalds.addQueue(customers);

        mcDonalds.startThread();
    }
}
