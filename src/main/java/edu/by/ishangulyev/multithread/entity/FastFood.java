package edu.by.ishangulyev.multithread.entity;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class FastFood
{
    private static FastFood INSTANCE;
    private static final AtomicBoolean atomicBoolean;
    private static final ReentrantLock reentrantLock;
    private List<LinkedList<Customer>> queues;
    private List<Customer> onlineOrder;

    static
    {
       reentrantLock = new ReentrantLock(true);
        atomicBoolean = new AtomicBoolean(false);
    }

    {
        onlineOrder = new ArrayList<>();
        queues = new ArrayList<>();
        queues.add(new LinkedList<>());
    }


    private FastFood(){}




    public void startThread()
    {
        ExecutorService offline = Executors.newFixedThreadPool(cusNumber());
        ExecutorService online = Executors.newFixedThreadPool(onlineOrder.size());

        for (int i = 0; i < queues.size(); i++)
        {
            for (int j = 0; j < queues.get(i).size(); j++)
            {
                offline.execute(queues.get(i).get(j));
            }
        }
        for (int i = 0; i < onlineOrder.size(); i++)
        {
            online.execute(onlineOrder.get(i));
        }
    }

    public static FastFood getInstance()
    {
        if(!atomicBoolean.get())
        {
            reentrantLock.lock();
            try
            {
                if(INSTANCE == null)
                {
                    INSTANCE = new FastFood();
                    atomicBoolean.set(true);
                }
            }
            finally
            {
                reentrantLock.unlock();
            }
        }
        return INSTANCE;
    }

    public void addOfflineCustomer(Customer customer)
    {
        if(customer.getState().equals(CustomerState.MAKE_ORDER))
        {
            queues.get(lessQueue()).add(customer);
        }
    }
    public void addOnlineCustomer(Customer customer)
    {
        if(customer.getState().equals(CustomerState.ONLINE))
        {
            onlineOrder.add(customer);
        }
    }

    public void getOfflineOrder(String ID)
    {
        reentrantLock.lock();
        try
        {
            TimeUnit.MILLISECONDS.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        if(checkOrderNumber(ID))
        {
            for (int i = 0; i < queues.size(); i++)
            {
                for (int j = 0; j < queues.get(i).size(); j++)
                {
                    queues.get(i).peek().setState(CustomerState.TAKE_ORDER);
                }
            }
        }
        reentrantLock.unlock();
    }

    public void getOnlineOrder(String id)
    {
        for (int i = 0; i < onlineOrder.size(); i++)
        {
            if(onlineOrder.get(i).getORDER_ID().equals(id))
            {
                onlineOrder.remove(i).setState(CustomerState.TAKE_ORDER);
            }
        }
    }
    public void createQueue()
    {
        queues.add(new LinkedList<>());
    }

    private int lessQueue()
    {
        int less = 0;
        int id = 0;

        for (int i = 0; i < queues.size(); i++)
        {
            if(less == 0 || queues.get(i).size() < less)
            {
                less = queues.get(i).size();
                id = i;
            }
        }
        return id;
    }

    public boolean checkOrderNumber(String order)
    {
        boolean result = false;
        for (int i = 0; i < queues.size(); i++)
        {
            for (int j = 0; j < queues.get(i).size(); j++)
            {
                if(queues.get(i).peek().getORDER_ID().equals(order))
                {
                    result = true;
                }
            }
        }
        return result;
    }

    private int cusNumber()
    {
        int count = 0;
        for (int i = 0; i < queues.size(); i++)
        {
            count+= queues.get(i).size();
        }
        return count;
    }

    public void addQueue(LinkedList<Customer> customers)
    {
        queues.add(customers);
    }

    public void setOnlineOrder(List<Customer> customers)
    {
        onlineOrder = customers;
    }
    public List<Queue<Customer>> getQueues()
    {
        return List.copyOf(queues);
    }
}