package br.com.test.ecommerce.authentication;

import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository
    extends
        JpaRepository<Session,Integer>
{
    Session findByToken(
        String token );

    Session findByCustomerIdAndExpirationDateGreaterThan(
        int id,
        DateTime now );
}
