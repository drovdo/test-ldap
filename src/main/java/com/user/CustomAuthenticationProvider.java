package com.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.Filter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private LdapTemplate ldapTemplate;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Filter filter = new EqualsFilter("uid", authentication.getName());
        Authentication auth = null;
        if (ldapTemplate.authenticate("", filter.encode(), authentication.getCredentials().toString())) {
            auth = new CustomAuthentication(authentication.getName(), authentication.getCredentials().toString(),
                    authentication.getAuthorities(),"darya", "rovdo", 1L);
        } else {
            //todo check the base
        }
        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
