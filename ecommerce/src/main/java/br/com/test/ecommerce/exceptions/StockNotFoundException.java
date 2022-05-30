package br.com.test.ecommerce.exceptions;

public class StockNotFoundException
    extends
        RuntimeException
{
    private static final long serialVersionUID = - 2186990687516619812L;

    public StockNotFoundException()
    {
        super( "Estoque não encontrado" );
    }
}
