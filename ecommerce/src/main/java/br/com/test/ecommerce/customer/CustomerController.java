package br.com.test.ecommerce.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "customer" )
public class CustomerController
{
    @Autowired
    private CustomerService service;

    @GetMapping( value = "search" )
    public ResponseEntity<List<PersistentCustomer>> getAll()
    {
        return ResponseEntity.ok( service.getAll() );
    }

    @GetMapping( value = "search/{id}" )
    public ResponseEntity<PersistentCustomer> getById(
        @PathVariable( "id" ) final int id )
    {
        return ResponseEntity.ok( service.getById( id ) );
    }

   

}
