package com.knockoutticket.backend.domain.requests;

import com.knockoutticket.backend.domain.models.UserType;
import lombok.Data;

import java.util.Set;

@Data
public class UpdateUserRolesRequest {
    private Set<UserType> userTypes;
}
