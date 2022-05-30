package br.com.test.ecommerce.authentication;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.google.common.base.MoreObjects;

public class AuthenticationCredentials
{

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;

    public AuthenticationCredentials()
    {
    }

    public AuthenticationCredentials(
        final String email,
        final String password )
    {
        this.email = email;
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper( this ).add( "Email", email ).add( "Senha", password ).toString();
    }

}
