package com.knockoutticket.backend.domain.models;

import lombok.*;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Administrator extends AppUser {
    private LocalDate promotionDate;
}
