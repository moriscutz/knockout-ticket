package com.knockoutticket.backend.config.security.token;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
