package com.knockoutticket.backend.config.security.token;

public interface AccessTokenDecoder {
    AccessToken decode(String accessTokenEncoded);
}
