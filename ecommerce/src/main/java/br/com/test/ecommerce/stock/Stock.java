package br.com.test.ecommerce.stock;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.test.ecommerce.product.Product;

@Entity
@Table( name = "stock" )
public class Stock

{
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "stock_sequence" )
    @SequenceGenerator( name = "stock_sequence", sequenceName = "stock_sequence", initialValue = 1, allocationSize = 1 )
    private Integer id;

    @Column
    private int quantity;

    @NotNull
    @OneToOne( fetch = FetchType.EAGER )
    // @JoinColumn( name = "product", foreignKey = @ForeignKey( name =
    // "fk_stock_product" ) )
    private Product product;

    public Stock()
    {

    }

    private Stock(
        final int quantity,
        final Product product )
    {
        this.quantity = quantity;
        this.product = product;

    }

    public static Stock create(
        final int quantity,
        final Product product )
    {

        return new Stock( quantity, product );
    }

    public Integer getId()
    {
        return id;
    }

    public Integer getQuantity()
    {

        return quantity;
    }

    public void setQuantity(
        final int quantity )
    {
        this.quantity = quantity;
    }

    public Product getProduct()
    {
        return product;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( getId() );
    }

    @Override
    public boolean equals(
        final Object obj )
    {
        if( this == obj ) {
            return true;
        }
        if( ! ( obj instanceof Stock ) ) {
            return false;
        }
        final Stock other = (Stock) obj;

        return Objects.equals( getId(), other.getId() ) &&
            Objects.equals( getQuantity(), other.getQuantity() ) &&
            Objects.equals( getProduct(), other.getProduct() );

    }

}
