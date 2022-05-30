package br.com.test.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.NOT_FOUND )
public class SessionNotFoundException
    extends
        RuntimeException
{

    private static final long serialVersionUID = - 6542140647363725848L;

    public SessionNotFoundException()
    {
        super( "Sessão não encontrada" );
    }

}
