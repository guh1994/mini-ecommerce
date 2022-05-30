package br.com.test.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST )
public class CustomerPasswordException
    extends
        RuntimeException
{

    private static final long serialVersionUID = - 4024177137592939419L;

    public CustomerPasswordException()
    {
        super( "Senha inválida" );
    }

}
