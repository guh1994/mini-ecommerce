package br.com.test.ecommerce.authentication;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.test.ecommerce.CustomerThreadLocal;

@WebFilter( urlPatterns = {
    "/product/*",
    "/category/*",
    "/customer/search",
    "/customer/update",
    "/customer/inactive",
    "/stock/*",
    "/cart/*",
    "/optimization/*"
} )
public class AuthenticationTokenFilter
    implements
        Filter
{

    private static final Logger logger = Logger.getLogger( AuthenticationTokenFilter.class.getName() );
    private static final String TOKEN_INVALIDO = "Token Inválido";
    private static final String CLIENTE_INATIVO = "Cliente Inativo";
    private static final String TIMEOUT = "Tempo de sessão Esgotado. Refaça o login";
    private static final String OK = "Ok";

    @Autowired
    private AuthenticationService sessionService;

    @Override
    public void init(
        final FilterConfig filterConfig )
        throws ServletException
    {
        logger.info( "Filtro criado" );
    }

    @Override
    public void doFilter(
        final ServletRequest request,
        final ServletResponse response,
        final FilterChain chain )
        throws IOException,
            ServletException
    {

        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;

        final String token = httpRequest.getHeader( "token" );
        if( token == null ) {
            httpResponse.sendError( HttpServletResponse.SC_FORBIDDEN, "Token não encontrado" );
            return;
        }

        final Session session = sessionService.getByToken( token );

        final String error = getError( session );
        if( error != OK ) {
            httpResponse.sendError( HttpServletResponse.SC_FORBIDDEN, error );
            return;
        }
        final long tempoInicial = System.currentTimeMillis();
        CustomerThreadLocal.set( session.getCustomer().getId() );
        try {
            chain.doFilter( request, response );
        } finally {
            CustomerThreadLocal.remove();
        }

        final long tempoFinal = System.currentTimeMillis();
        final String uri = ( (HttpServletRequest) request ).getRequestURI();

        logger.info( getFormmatedLog( tempoInicial, tempoFinal, uri ) );

    }

    private String getFormmatedLog(
        final long tempoInicial,
        final long tempoFinal,
        final String uri )
    {

        return "\n Tempo da requisicao de da URI " + uri
            + " Demorou " + ( tempoFinal - tempoInicial ) + " ms \n";
    }

    private String getError(
        final Session session )
    {
        if( session == null ) {
            return TOKEN_INVALIDO;
        }
        if( session.getCustomer().getInactive() == true ) {
            return CLIENTE_INATIVO;
        }

        if( session.getExpirationDate().isBeforeNow() ) {
            return TIMEOUT;
        }
        return OK;
    }

    @Override
    public void destroy()
    {
        logger.warning( "Filtro finalizado" );
    }

}