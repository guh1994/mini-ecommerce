package br.com.test.ecommerce.stock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import br.com.test.ecommerce.exceptions.StockDuplicatedProductException;
import br.com.test.ecommerce.exceptions.StockFillException;
import br.com.test.ecommerce.exceptions.StockNotFoundException;
import br.com.test.ecommerce.exceptions.StockProductRemoveWithQuantityException;
import br.com.test.ecommerce.product.Product;
import br.com.test.ecommerce.product.ProductService;

@Component
public class StockService
{
    @Autowired
    private StockRepository repository;

    @Autowired
    private ProductService productService;

    public List<Stock> getStock()
    {
        return repository.findAll();
    }

    public Page<Stock> findPage(
        final int page,
        final int size )
    {
        return repository.findAll( PageRequest.of( page, size ) );
    }

    public Stock getStockByProduct(
        final Product product )
    {
        final Stock stock = repository.findByProduct( product );
        if( stock == null ) {
            throw new StockNotFoundException();
        }
        return stock;
    }

    public Stock getStockByProductId(
        final int idProduct )
    {
        final Stock stock = repository.findByProductId( idProduct );
        if( stock == null ) {
            throw new StockNotFoundException();
        }
        return stock;
    }

    public List<Stock> getStockByQuantityGreaterThanZero()
    {
        return repository.findByQuantityGreaterThan( 0 );
    }

    public List<Stock> getStockByQuantityLessThanOne()
    {
        return repository.findByQuantityLessThan( 1 );
    }

    public Stock getStockMaxProduct()
    {
        return repository.maxProduct();
    }

    public Stock getStockMinProduct()
    {
        return repository.minProduct();
    }

    public Stock createStock(
        final StockItem stockItem )
    {
        if( stockItem.getQuantity() < 1 ) {
            throw new StockFillException( "Quantidade deve ser maior que 1" );
        }
        final int productId = stockItem.getProductId();
        if( repository.findByProductId( productId ) != null ) {
            throw new StockDuplicatedProductException();
        }
        final Product product = productService.getProductById( productId );
        return repository.save( Stock.create( stockItem.getQuantity(), product ) );
    }

    public Stock addStock(
        final StockItem stockItem )
    {
        if( stockItem.getQuantity() < 1 ) {
            throw new StockFillException( "Quantidade deve ser maior que 1" );
        }

        final Product product = productService.getProductById( stockItem.getProductId() );

        final Stock stock = getStockByProduct( product );

        final long quantitySum = Long.sum( stockItem.getQuantity(), stock.getQuantity() );

        if( quantitySum > Integer.MAX_VALUE ) {
            throw new StockFillException( "Quantidade informada é maior do que a permitida" );
        }
        stock.setQuantity( stock.getQuantity() + stockItem.getQuantity() );
        return repository.save( stock );
    }

    public Stock removeStock(
        final StockItem stockItem )
    {
        final Product product = productService.getProductById( stockItem.getProductId() );
        final Stock stock = getStockByProduct( product );

        if( stockItem.getQuantity() > stock.getQuantity() ) {
            throw new StockFillException( "Quantidade informada é maior que estoque" );
        }
        stock.setQuantity( stock.getQuantity() - stockItem.getQuantity() );
        return repository.save( stock );
    }

    public boolean deleteStock(
        final int stockId )
    {
        if( ! repository.existsById( stockId ) ) {
            throw new StockNotFoundException();
        }

        final Stock stock = repository.findById( stockId );
        if( stock.getQuantity() > 0 ) {
            throw new StockProductRemoveWithQuantityException();
        }
        repository.deleteById( stockId );
        return ! repository.existsById( stockId );
    }
}
