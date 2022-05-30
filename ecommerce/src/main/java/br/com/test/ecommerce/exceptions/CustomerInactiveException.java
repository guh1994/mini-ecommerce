package br.com.test.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST )
public class CustomerInactiveException
    extends
        RuntimeException
{

    private static final long serialVersionUID = 835300638075884435L;

    public CustomerInactiveException()
    {
        super( "Usuário Inativo" );
    }

}
