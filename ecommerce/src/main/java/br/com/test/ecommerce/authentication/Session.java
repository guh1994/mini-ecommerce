package br.com.test.ecommerce.authentication;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import br.com.test.ecommerce.customer.PersistentCustomer;

@Entity
@Table( name = "session" )
public class Session
{

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "session_sequence" )
    @SequenceGenerator( name = "session_sequence", sequenceName = "session_sequence", initialValue = 1, allocationSize = 1 )
    private Integer id;
    @NotBlank
    private String token;
    @NotNull
    @Column( name = "expiration_date" )
    @Type( type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime" )
    private DateTime expirationDate;
    @NotNull
    @Column( name = "login_date" )
    @Type( type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime" )
    private DateTime loginDate;
    @NotNull
    @OneToOne( fetch = FetchType.EAGER )
    @JoinColumn( name = "customer", foreignKey = @ForeignKey( name = "fk_session_customer" ) )
    private PersistentCustomer customer;

    public Session()
    {
    }

    public static Session start(
        final String token,
        final DateTime loginDate,
        final DateTime expirationDate,
        final PersistentCustomer user )
    {

        return new Session( token, loginDate, expirationDate, user );

    }

    private Session(
        final String token,
        final DateTime loginDate,
        final DateTime expirationDate,
        final PersistentCustomer user )
    {
        this.token = token;
        this.expirationDate = expirationDate;
        this.loginDate = loginDate;
        this.customer = user;
    }

    public Integer getId()
    {
        return id;
    }

    public String getToken()
    {
        return token;
    }

    public DateTime getExpirationDate()
    {
        return expirationDate;
    }

    public DateTime getLoginDate()
    {
        return loginDate;
    }

    public void setExpirationDate(
        final DateTime expirationDate )
    {
        this.expirationDate = expirationDate;
    }

    public PersistentCustomer getCustomer()
    {
        return customer;
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode( getId(), getToken() );
    }

    @Override
    public boolean equals(
        final Object obj )
    {

        if( this == obj ) {
            return true;
        }

        if( ! ( obj instanceof Session ) ) {
            return true;
        }

        final Session session = (Session) obj;

        return Objects.equal( getId(), session.getId() ) &&
            Objects.equal( getToken(), session.getToken() ) &&
            Objects.equal( getExpirationDate(), session.getExpirationDate() ) &&
            Objects.equal( getLoginDate(), session.getLoginDate() ) &&
            Objects.equal( getCustomer(), session.getCustomer() );
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper( this )
            .add( "Id", id )
            .add( "Token", token )
            .add( "Expiration Date", expirationDate )
            .add( "Login Date", loginDate )
            .add( "User", customer ).toString();
    }

}
