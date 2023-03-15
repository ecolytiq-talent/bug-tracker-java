package com.ecolytiq.bugtracker.domain;

import com.ecolytiq.bugtracker.persistence.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.util.List;

import static com.ecolytiq.bugtracker.domain.Status.CODE_REVIEW;
import static com.ecolytiq.bugtracker.domain.Status.OPEN;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TicketServiceTest {

    private final TicketService ticketService;
    private final TicketRepository ticketRepository;

    @BeforeEach
    public void cleanUp() {
        ticketRepository.deleteAll();
    }

    @Test
    void getAllTickets_noTicketsArePresent_returnsEmptyList() {
        List<Ticket> actualTickets = ticketService.getAllTickets();

        assertThat(actualTickets).hasSize(0);
    }

    @Test
    void getAllTickets_ticketsArePresent_returnsAllTickets() {
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
        ticketRepository.saveAll(givenTickets);

        List<Ticket> actualTickets = ticketService.getAllTickets();

        assertThat(actualTickets).hasSize(2);
        assertThat(actualTickets).usingRecursiveComparison().isEqualTo(givenTickets);
    }
}
