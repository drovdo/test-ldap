package com.user;

import com.attributesMappers.UserAttributesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.Filter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.authentication.LdapAuthenticator;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private BindAuthenticator bindAuthenticator;

    private LdapAuthenticationProvider ldapAuthenticationProvider;

    @Autowired
    private LdapTemplate ldapTemplate;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        ldapAuthenticationProvider = new LdapAuthenticationProvider(bindAuthenticator);
        Authentication auth;
        try {
            auth = ldapAuthenticationProvider.authenticate(authentication);
            //todo add user if it's needed
            //ldapTemplate.search(query().where("uid").is(authentication.getName()), new UserAttributesMapper());
        } catch(BadCredentialsException b) {
            //todo check the base
            auth = new CustomAuthentication(authentication.getName(), authentication.getCredentials().toString(),
                    authentication.getAuthorities(), "darya", "rovdo", 1L);
        }
        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
