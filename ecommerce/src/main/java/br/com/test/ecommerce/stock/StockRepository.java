package br.com.test.ecommerce.stock;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.test.ecommerce.product.Product;

@Repository
public interface StockRepository
    extends
        JpaRepository<Stock,Integer>
{
    List<Stock> findByQuantityGreaterThan(
        final int quantity );

    List<Stock> findByQuantityLessThan(
        final int quantity );

    @Query( "SELECT s FROM Stock s WHERE s.quantity = (SELECT MAX(z.quantity) from Stock z)" )
    Stock maxProduct();

    @Query( "SELECT s FROM Stock s WHERE s.quantity = (SELECT MIN(z.quantity) from Stock z)" )
    Stock minProduct();

    Stock findByProduct(
        Product product );

    boolean existByProductId(
        int productId );

    Stock findByProductId(
        int productId );

    Stock findById(
        int productId );

    List<Stock> findByProductPricelessThanEqualOrderByProductPriceAsc(
        long value );
}
