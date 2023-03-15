package com.ecolytiq.bugtracker.rest;

import com.ecolytiq.bugtracker.domain.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketDtoConverter {

    public TicketDto convertToTicketDto(Ticket ticket) {
        return TicketDto.builder()
                .id(ticket.getId())
                .title(ticket.getTitle())
                .status(ticket.getStatus())
                .createdAt(ticket.getCreatedAt())
                .build();
    }
}
