package com.knockoutticket.backend.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Data
@Entity
public class AppUser {
    @Id
    private Long id;
    private String email;
    private String password;
    private UserType userType;
}
