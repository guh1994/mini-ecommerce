package br.com.test.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST )
public class StockDuplicatedProductException
    extends
        RuntimeException
{

    private static final long serialVersionUID = - 7401482767420457562L;

    public StockDuplicatedProductException()
    {
        super( "Estoque Duplicado. O produto já está em outro estoque." );
    }

}
