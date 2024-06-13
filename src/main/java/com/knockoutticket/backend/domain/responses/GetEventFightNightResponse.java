package com.knockoutticket.backend.domain.responses;

import com.knockoutticket.backend.domain.models.Event;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
public class GetEventFightNightResponse {
    private Long id;
    private String title;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String place;
    private List<Event> events;
}