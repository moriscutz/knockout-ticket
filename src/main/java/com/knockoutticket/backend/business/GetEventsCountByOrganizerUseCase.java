package com.knockoutticket.backend.business;

import com.knockoutticket.backend.domain.responses.GetEventsCountByOrganizerResponse;

import java.util.List;

public interface GetEventsCountByOrganizerUseCase {
    List<GetEventsCountByOrganizerResponse> countEventsByOrganizer();
}
