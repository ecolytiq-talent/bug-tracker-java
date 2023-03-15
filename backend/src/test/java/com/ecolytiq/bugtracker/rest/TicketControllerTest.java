package com.ecolytiq.bugtracker.rest;

import com.ecolytiq.bugtracker.domain.Ticket;
import com.ecolytiq.bugtracker.domain.TicketService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.util.List;

import static com.ecolytiq.bugtracker.domain.Status.CODE_REVIEW;
import static com.ecolytiq.bugtracker.domain.Status.OPEN;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@MockBean(TicketService.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TicketControllerTest {

    private final MockMvc mockMvc;
    private final TicketService ticketService;

    @Test
    void getAllTickets_noTicketsArePresent_returnsEmptyList() throws Exception {
        when(ticketService.getAllTickets()).thenReturn(emptyList());

        mockMvc.perform(get("/api/v1/tickets"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(APPLICATION_JSON),
                        content().json("[]")
                );
    }

    @Test
    void getAllTickets_ticketsArePresent_returnsAllTickets() throws Exception {
        List<Ticket> givenTickets = asList(
                new Ticket(
                        "BUG-123",
                        "Save Button not responding",
                        CODE_REVIEW,
                        OffsetDateTime.parse("2023-02-15T10:25:43.785+01:00")
                ),
                new Ticket(
                        "BUG-456",
                        "Incorrect password reset link",
                        OPEN,
                        OffsetDateTime.parse("2023-02-27T15:36:26.517+01:00")
                )
        );
        when(ticketService.getAllTickets()).thenReturn(givenTickets);


        mockMvc.perform(get("/api/v1/tickets"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(APPLICATION_JSON),
                        jsonPath("$", hasSize(2)),
                        jsonPath("$[0].id").value("BUG-123"),
                        jsonPath("$[0].title").value("Save Button not responding"),
                        jsonPath("$[0].status").value("CODE_REVIEW"),
                        jsonPath("$[0].created_at").value("2023-02-15T10:25:43.785+01:00"),
                        jsonPath("$[1].id").value("BUG-456"),
                        jsonPath("$[1].title").value("Incorrect password reset link"),
                        jsonPath("$[1].status").value("OPEN"),
                        jsonPath("$[1].created_at").value("2023-02-27T15:36:26.517+01:00")
                );
    }
}
