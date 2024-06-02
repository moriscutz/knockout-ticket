package com.knockoutticket.backend.domain.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateBoxerResponse {
    private Long id;
    private String fullName;
    private Integer wins;
    private Integer losses;
    private Integer draws;
}
