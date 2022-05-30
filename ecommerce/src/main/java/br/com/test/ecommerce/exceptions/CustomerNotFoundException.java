package br.com.test.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.NOT_FOUND )
public class CustomerNotFoundException
    extends
        RuntimeException
{
    private static final long serialVersionUID = - 135030166210490592L;

    public CustomerNotFoundException()
    {
        super( "Cliente não encontrado" );
    }
}
