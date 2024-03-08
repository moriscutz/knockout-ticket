package com.knockoutticket.backend.domain.user;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
public class EventOrganizer extends AppUser {
    private String organizationName;
}
