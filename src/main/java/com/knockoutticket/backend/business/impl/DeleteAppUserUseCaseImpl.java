package com.knockoutticket.backend.business.impl;

import com.knockoutticket.backend.business.DeleteAppUserUseCase;
import com.knockoutticket.backend.business.DeleteArchivedEventsForOrganizerUseCase;
import com.knockoutticket.backend.business.DeleteBookingsForUserUseCase;
import com.knockoutticket.backend.business.DeleteEventsForOrganizerUseCase;
import com.knockoutticket.backend.business.exception.UserNotFoundException;
import com.knockoutticket.backend.domain.models.UserType;
import com.knockoutticket.backend.persistence.AppUserRepository;
import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import com.knockoutticket.backend.persistence.entity.UserTypeEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DeleteAppUserUseCaseImpl implements DeleteAppUserUseCase {

    private final AppUserRepository appUserRepository;
    private final DeleteBookingsForUserUseCase deleteBookingsForUserUseCase;
    private final DeleteArchivedEventsForOrganizerUseCase deleteArchivedEventsForOrganizerUseCase;
    private final DeleteEventsForOrganizerUseCase deleteEventsForOrganizerUseCase;
    private final UserConverter userConverter;

    @Transactional
    @Override
    public void deleteAppUser(Long id) {
        AppUserEntity user = appUserRepository.findById(id).orElseThrow(UserNotFoundException::new);

        deleteConnectedObjects(id, user.getUserRoles());

        appUserRepository.deleteById(id);
    }

    private void deleteConnectedObjects(Long userId, Set<UserTypeEntity> roles) {
        Set<UserType> userTypes = roles.stream()
                .map(UserTypeEntity::getType)
                .collect(Collectors.toSet());

        if (userTypes.contains(UserType.NORMAL_USER)) {
            deleteBookingsForUserUseCase.deleteBookingsForUser(userId);
        }
        if (userTypes.contains(UserType.EVENT_ORGANIZER)) {
            deleteArchivedEventsForOrganizerUseCase.deleteArchivedEventsForOrganizer(userId);
            deleteEventsForOrganizerUseCase.deleteEventsForOrganizer(userId);
            deleteBookingsForUserUseCase.deleteBookingsForUser(userId);
        }
    }
}
