package com.attributesMappers;

import com.user.CustomAuthentication;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import java.util.ArrayList;
import java.util.List;

public class UserAttributesMapper implements AttributesMapper<CustomAuthentication> {

    @Override
    public CustomAuthentication mapFromAttributes(Attributes attributes) throws NamingException {
        if (attributes == null) {
            return null;
        }

        String uid = null;

        if (attributes.get("uid") != null) {
            uid = attributes.get("uid").get().toString();
        }
        List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority("ADMIN"));
        //todo get user info
        return null;
    }
}
