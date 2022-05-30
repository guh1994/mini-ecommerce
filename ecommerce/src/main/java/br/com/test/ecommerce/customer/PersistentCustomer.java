package br.com.test.ecommerce.customer;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.google.common.base.MoreObjects;

@Entity
@Table( name = "customer" )
public class PersistentCustomer
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;
    @NotBlank
    @Pattern( regexp = "[a-z0-9]+[a-z0-9.]+@[a-z0-9]+\\.[a-z0-9]+(\\.[a-z0-9]+)?", message = "Formato de email inválido" )
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    private boolean inactive;

    public PersistentCustomer()
    {
    }

    public static PersistentCustomer create(
        final Integer id,
        final String email,
        final String name,
        final String password,
        final boolean inactive )
    {
        return new PersistentCustomer( id, email, name, password, inactive );
    }

    private PersistentCustomer(
        final Integer id,
        @NotBlank @Pattern( regexp = "[a-z0-9]+[a-z0-9.]+@[a-z0-9]+\\.[a-z0-9]+(\\.[a-z0-9]+)?",
            message = "Formato de email inválido" ) final String email,
        @NotBlank final String name,
        @NotBlank final String password,
        final boolean inactive )
    {
        super();
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.inactive = inactive;
    }

    public Integer getId()
    {
        return id;
    }

    public String getEmail()
    {
        return email;
    }

    public String getName()
    {
        return name;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(
        final String password )
    {
        this.password = password;
    }

    public boolean getInactive()
    {
        return inactive;
    }

    public void setInactive(
        final boolean inactive )
    {
        this.inactive = inactive;
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

        if( ! ( obj instanceof PersistentCustomer ) ) {
            return false;
        }

        final PersistentCustomer user = (PersistentCustomer) obj;
        return Objects.equals( getId(), user.getId() ) &&
            Objects.equals( getEmail(), user.getEmail() ) &&
            Objects.equals( getPassword(), user.getPassword() ) &&
            Objects.equals( getName(), user.getName() ) &&
            Objects.equals( getInactive(), user.getInactive() );
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper( this )
            .add( "Id", id )
            .add( "Email", email )
            .add( "Name", name )
            .add( "Password", password )
            .add( "Active", inactive )
            .toString();
    }

}
