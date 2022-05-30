package br.com.test.ecommerce.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository
    extends
        JpaRepository<Product,Integer>
{
    Product findByCode(
        final int code );

    Product findById(
        final int id );

    List<Product> findByPriceGreaterThan(
        double price );

    List<Product> findByPriceLessThan(
        double price );

    List<Product> findByNameContainingIgnoreCase(
        final String name );

}
