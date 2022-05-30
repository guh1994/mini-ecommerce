package br.com.test.ecommerce.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository
    extends
        JpaRepository<PersistentCustomer,Integer>
{

    PersistentCustomer findById(
        final int id );

    PersistentCustomer findByIdAndInactiveFalse(
        final int id );

    PersistentCustomer findByEmail(
        final String email );

    PersistentCustomer findByEmailAndInactiveFalse(
        final String email );

    boolean existsByEmailAndIdNotAndInactiveFalse(
        final String email,
        final int id );

}
