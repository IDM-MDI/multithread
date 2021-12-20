package edu.by.ishangulyev.multithread.entity;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

public class Customer implements Runnable
{
    private final String ORDER_ID;
    private final FastFood fastFood = FastFood.getInstance();

    private String name;
    private int age;

    private CustomerState state;

    {
        this.ORDER_ID = UUID.randomUUID().toString();

    }

    public Customer() {}

    public Customer(String name, int age, CustomerState state)
    {
        this.name = name;
        this.age = age;
        this.state = state;
    }

    @Override
    public void run()
    {
        if(state.equals(CustomerState.ONLINE))
        {
            fastFood.getOnlineOrder(ORDER_ID);
            System.out.println(name + " get online order");
        }
        else
        {
            fastFood.getOfflineOrder(ORDER_ID);
            System.out.println(name + " get offline order");
        }
    }

    public String getORDER_ID()
    {
        return ORDER_ID;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public CustomerState getState()
    {
        return state;
    }

    public void setState(CustomerState state)
    {
        this.state = state;
    }
}
