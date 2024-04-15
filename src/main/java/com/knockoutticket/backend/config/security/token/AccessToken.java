package com.knockoutticket.backend.config.security.token;

import java.util.Set;

public interface AccessToken {
    String getSubject();

    Long getUserId();

    Set<String> getRoles();
    boolean hasRole(String roleName);
}