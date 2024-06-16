package com.knockoutticket.backend.domain.responses;

import com.knockoutticket.backend.persistence.entity.AppUserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class GetEventsCountByOrganizerResponse {
    private AppUserEntity organizer;
    private Long eventCount;

}
