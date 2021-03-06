package br.com.test.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.NOT_FOUND )
public class ProductNotFoundException
    extends
        RuntimeException
{
    private static final long serialVersionUID = - 2506282196572872844L;

    public ProductNotFoundException()
    {
        super( "Produto n?o encontrado" );
    }
}
