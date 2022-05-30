package br.com.test.ecommerce.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.test.ecommerce.exceptions.ProductDuplicatedCodeException;
import br.com.test.ecommerce.exceptions.ProductNotFoundException;
import br.com.test.ecommerce.exceptions.StockProductRemoveWithQuantityException;
import br.com.test.ecommerce.stock.Stock;
import br.com.test.ecommerce.stock.StockRepository;

@Component
public class ProductService
{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockRepository stockRepository;

    public Product getProductByCode(
        final int code )
    {
        final Product product = productRepository.findByCode( code );
        if( product == null ) {

            throw new ProductNotFoundException();
        }
        return product;
    }

    public Product getProductById(
        final int id )
    {
        final Product product = productRepository.findById( id );
        if( product == null ) {

            throw new ProductNotFoundException();
        }
        return product;
    }

    public List<Product> getProducts()
    {
        return productRepository.findAll();
    }

    public List<Product> findByPriceGreaterThan(
        final double price )
    {
        return productRepository.findByPriceGreaterThan( price );
    }

    public List<Product> findByPriceLessThan(
        final double price )
    {
        return productRepository.findByPriceLessThan( price );
    }

    public List<Product> getProductsByContainingWord(
        final String word )
    {
        return productRepository.findByNameContainingIgnoreCase( word );
    }

    public Product save(
        final Product product )
    {
        if( productRepository.findByCode( product.getCode() ) != null ) {
            throw new ProductDuplicatedCodeException();
        }
        return productRepository.save( product );
    }

    public Product update(
        final Product product )
    {
        if( ! productRepository.existsById( product.getId() ) ) {

            throw new ProductNotFoundException();
        }
        final Product currentProduct = productRepository.findByCode( product.getCode() );

        if( currentProduct != null && currentProduct.getId() != product.getId() ) {
            throw new ProductDuplicatedCodeException();
        }
        return productRepository.save( product );
    }

    public boolean delete(
        final int id )
    {
        if( productRepository.findById( id ) == null ) {
            throw new ProductNotFoundException();
        }

        final Stock stock = stockRepository.findByProductId( id );

        if( stock != null && stock.getQuantity() > 0 ) {
            throw new StockProductRemoveWithQuantityException();
        }
        if( stock != null ) {
            stockRepository.delete( stock );
        }
        productRepository.deleteById( id );

        return ! productRepository.existsById( id );
    }

}
