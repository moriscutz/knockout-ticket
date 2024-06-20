package com.knockoutticket.backend.business;

import com.knockoutticket.backend.domain.requests.UpdateUserRolesRequest;
import com.knockoutticket.backend.domain.responses.UpdateUserRolesResponse;

public interface UpdateUserRolesAsAdminUseCase {
    UpdateUserRolesResponse updateUserRoles(Long userId, UpdateUserRolesRequest request);
}
