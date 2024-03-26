package com.knockoutticket.backend.domain.models;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventOrganizer extends AppUser {
    private String organizationName;
}
