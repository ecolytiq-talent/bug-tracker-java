package com.ecolytiq.bugtracker.rest;

import com.ecolytiq.bugtracker.domain.Status;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class TicketDto {
    private String id;
    private String title;
    private Status status;
    private OffsetDateTime createdAt;
}
