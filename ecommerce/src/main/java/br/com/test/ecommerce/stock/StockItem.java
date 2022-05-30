package br.com.test.ecommerce.stock;

import javax.validation.constraints.NotNull;

import com.google.common.base.MoreObjects;

public class StockItem
{

    @NotNull
    private Integer productId;
    private int quantity;

    public StockItem()
    {
    }

    public static StockItem create(
        final Integer productId,
        final int quantity )
    {
        return new StockItem( productId, quantity );
    }

    private StockItem(
        final Integer productId,
        final int quantity )
    {
        super();
        this.productId = productId;
        this.quantity = quantity;
    }

    public Integer getProductId()
    {
        return productId;
    }

    public void setProductId(
        final Integer idProduct )
    {
        this.productId = idProduct;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(
        final int quantity )
    {
        this.quantity = quantity;
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper( this ).add( "Product ID", productId )
            .add( "Quantity", quantity ).toString();
    }
}