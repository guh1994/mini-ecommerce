package br.com.test.ecommerce.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerService
{
    @Autowired
    private CustomerRepository repository;

    public List<PersistentCustomer> getAll()
    {
        return repository.findAll();
    }

    public PersistentCustomer getById(
        final int id )
    {
        return repository.findById( id );
    }

    

}
