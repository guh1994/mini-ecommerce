package br.com.test.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST )
public class StockProductRemoveWithQuantityException
    extends
        RuntimeException
{
    private static final long serialVersionUID = 7243396709225776929L;

    public StockProductRemoveWithQuantityException()
    {
        super( "Não é possivel remover um estoque com produtos com quantidade superior a 0" );
    }
}
