package com.ecolytiq.bugtracker.rest;

import com.ecolytiq.bugtracker.domain.Ticket;
import com.ecolytiq.bugtracker.domain.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private final TicketDtoConverter ticketDtoConverter;

    @GetMapping(path = ("/api/v1/tickets"), produces = (APPLICATION_JSON_VALUE))
    public ResponseEntity<List<TicketDto>> getAllTickets() {
        List<Ticket> allTickets = ticketService.getAllTickets();

        List<TicketDto> response = allTickets.stream().map(ticketDtoConverter::convertToTicketDto).toList();

        return ResponseEntity.ok(response);
    }
}
