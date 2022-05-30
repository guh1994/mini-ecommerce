package br.com.test.ecommerce.product;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

@Entity
@Table( name = "product" )

public class Product
{

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "product_sequence" )
    @SequenceGenerator( name = "product_sequence", sequenceName = "product_sequence", initialValue = 1, allocationSize = 1 )
    @Column( updatable = false )
    private Integer id;

    @NotNull
    @Column
    private Integer code;

    @NotBlank
    @Column
    private String name;

    @Min( message = "The value do not be less 1 cent", value = 0 )
    @Column
    private Double price;

    @Column
    private String description;

    @Column
    private String size;

    public Product()
    {
    }

    public static Product create(
        final String name,
        final Integer code,
        final Double price,
        final String description,
        final String size )
    {
        return new Product( name, code, price, description, size );
    }

    @JsonCreator
    private Product(
        @JsonProperty( "name" ) final String name,
        @JsonProperty( "code" ) final Integer code,
        @JsonProperty( "price" ) final Double price,
        @JsonProperty( "description" ) final String description,
        @JsonProperty( "size" ) final String size )
    {
        this.name = name;
        this.code = code;
        this.price = price;
        this.description = description;
        this.size = size;

    }

    public Integer getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public Integer getCode()
    {
        return code;
    }

    public String getDescription()
    {
        return description;
    }

    public Double getPrice()
    {
        return price;
    }

    public String getSize()
    {
        return size;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash( getId(), getCode() );

    }

    @Override
    public boolean equals(
        final Object obj )
    {
        if( this == obj ) {
            return true;
        }
        if( ! ( obj instanceof Product ) ) {
            return false;
        }

        final Product other = (Product) obj;
        return Objects.equals( getId(), other.getId() ) &&
            Objects.equals( getCode(), other.getCode() ) &&
            Objects.equals( getDescription(), other.getDescription() ) &&
            Objects.equals( getName(), other.getName() ) &&
            Objects.equals( getPrice(), other.getPrice() ) &&
            Objects.equals( getSize(), other.getSize() );
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper( this ).add( "Id", id ).add( "Code", code ).add( "Name", name ).add( "Price", price ).add(
            "Description", description ).toString();
    }

}
