package edu.by.ishangulyev.multithread.reader.impl;

import edu.by.ishangulyev.multithread.builder.CustomerBuilder;
import edu.by.ishangulyev.multithread.builder.impl.CustomerBuilderImpl;
import edu.by.ishangulyev.multithread.entity.Customer;
import edu.by.ishangulyev.multithread.entity.CustomerState;
import edu.by.ishangulyev.multithread.reader.CustomerReaderBuilder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class CustomerReaderBuilderImpl implements CustomerReaderBuilder
{
    private static final Logger logger = LogManager.getLogger();
    private String path;
    private Properties properties;
    private List<Customer> customers;
    @Override
    public CustomerReaderBuilder setPath(String path)
    {
        this.path = path;
        return this;
    }

    @Override
    public CustomerReaderBuilder read()
    {
        try(InputStream input = new FileInputStream(path))
        {
            properties = new Properties();
            properties.load(input);

            customers = getInfo();

        }
        catch (IOException e)
        {
            e.printStackTrace();
            logger.log(Level.ERROR,"Something get wrong while reading",e);
        }
        finally
        {
            logger.log(Level.INFO,"File successfully read");
        }
        return this;
    }

    private List<Customer> getInfo()
    {
        List<Customer> result = new ArrayList<>();
        CustomerBuilder builder = new CustomerBuilderImpl();
        String name = ".name";
        String age = ".age";
        String type = ".state";

        for (int i = 0; i < properties.size()/3; i++)
        {
            result.add(builder.setName(properties.getProperty("customer"+(i+1)+name))
                    .setAge(Integer.parseInt(properties.getProperty("customer"+(i+1)+age)))
                    .setCustomerState(CustomerState.valueOf(properties.getProperty("customer"+(i+1)+type).toUpperCase()))
                    .build());
        }
        return result;
    }


    @Override
    public List<Customer> getResult()
    {
        return List.copyOf(customers);
    }
}
