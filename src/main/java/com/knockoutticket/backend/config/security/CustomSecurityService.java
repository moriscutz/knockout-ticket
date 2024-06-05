package com.knockoutticket.backend.config.security;

import com.knockoutticket.backend.config.security.token.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class CustomSecurityService {

    private final AccessToken accessToken;

    @Autowired
    public CustomSecurityService(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public boolean isAccountIdMatching(Long accountId, Authentication authentication){
        if(authentication == null || this.accessToken == null){
            return false;
        }

        Long accountIdFromToken = this.accessToken.getUserId();
        return accountId != null && accountIdFromToken!=null && accountId.equals(accountIdFromToken);
    }
}
