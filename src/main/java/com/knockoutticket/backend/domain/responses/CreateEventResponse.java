package com.knockoutticket.backend.domain.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Builder
@Getter
public class CreateEventResponse {
    private Long id;
    private String message;
}
