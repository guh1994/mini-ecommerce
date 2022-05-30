package br.com.test.ecommerce.authentication;

import static br.com.test.ecommerce.authentication.Encryptor.encryptPassword;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.test.ecommerce.customer.CustomerRepository;
import br.com.test.ecommerce.customer.PersistentCustomer;
import br.com.test.ecommerce.exceptions.CustomerInactiveException;
import br.com.test.ecommerce.exceptions.CustomerNotFoundException;
import br.com.test.ecommerce.exceptions.CustomerPasswordException;
import br.com.test.ecommerce.exceptions.SessionNotFoundException;

@Component
public class AuthenticationService
{
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SessionRepository sessionRepository;

    public String login(
        final AuthenticationCredentials customerLogin ) {
    
        final PersistentCustomer customer = customerRepository.findByEmail( customerLogin.getEmail() );

        if( customer == null ) {
            throw new CustomerNotFoundException();
        }
        if( customer.getInactive() == true ) {
            throw new CustomerInactiveException();
        }
        if( ! customer.getPassword().equals( encryptPassword( customerLogin.getPassword() ) ) ) {
            throw new CustomerPasswordException();
        }

        return generateSession( customer );
    }

    public String logout(
        final String token )
    {
        final Session session = sessionRepository.findByToken( token );
        if( session == null ) {
            throw new SessionNotFoundException();
        }
        session.setExpirationDate( DateTime.now() );
        sessionRepository.save( session );

        return "Sessão encerrada";
    }

    private String generateSession(
        final PersistentCustomer customer )
    {
        final DateTime dateTime = DateTime.now();

        final Session session = sessionRepository.findByCustomerIdAndExpirationDateGreaterThan( customer.getId(), DateTime.now() );

        if( session != null ) {
            return session.getToken();
        }

        final Session newSession = Session.start( generateToken(), dateTime, dateTime.plusHours( 2 ), customer );
        sessionRepository.save( newSession );
        return newSession.getToken();
    }

    public Session getByToken(
        final String token )
    {
        return sessionRepository.findByToken( token );
    }

    private static String generateToken()
    {
        return String.valueOf( Double.valueOf( Math.random() * 1000 ).longValue() + System.currentTimeMillis() );
    }

}
