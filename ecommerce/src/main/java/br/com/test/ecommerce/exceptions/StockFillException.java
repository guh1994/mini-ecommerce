package br.com.test.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST )
public class StockFillException
    extends
        RuntimeException
{

    private static final long serialVersionUID = - 5509549744314797256L;

    public StockFillException(
        final String message )
    {
        super( message );
    }

}
