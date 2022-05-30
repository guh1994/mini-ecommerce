package br.com.test.ecommerce.authentication;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "authentication" )
public class AuthenticationController
{
    @Autowired
    private AuthenticationService sessionService;

    @RequestMapping( value = "login" )
    public ResponseEntity<String> login(
        @RequestBody @Valid final AuthenticationCredentials customer )
    {
        return ResponseEntity.ok( sessionService.login( customer ) );
    }

    @RequestMapping( value = "logout" )
    public ResponseEntity<String> logout(
        @RequestHeader final String token )
    {
        return ResponseEntity.ok( sessionService.logout( token ) );
    }

}
