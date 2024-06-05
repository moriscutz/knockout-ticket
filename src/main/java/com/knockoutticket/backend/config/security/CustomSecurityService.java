package com.knockoutticket.backend.config.security;

import com.knockoutticket.backend.config.security.token.AccessToken;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomSecurityService {

    @Autowired
    private AccessToken accessToken;

    public boolean isAccountIdMatching(Long accountId, Authentication authentication){
        if(authentication == null || this.accessToken == null){
            return false;
        }

        Long accountIdFromToken = this.accessToken.getUserId();
        return accountId != null && accountIdFromToken!=null && accountId.equals(accountIdFromToken);
    }
}
