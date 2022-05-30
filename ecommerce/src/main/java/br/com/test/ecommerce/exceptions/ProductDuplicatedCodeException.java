package br.com.test.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST )
public class ProductDuplicatedCodeException
    extends
        RuntimeException
{
    private static final long serialVersionUID = 1L;

    public ProductDuplicatedCodeException()
    {
        super( "Produto com código duplicado" );
    }
}
