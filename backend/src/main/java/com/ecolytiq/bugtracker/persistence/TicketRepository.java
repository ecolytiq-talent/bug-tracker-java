package com.ecolytiq.bugtracker.persistence;

import com.ecolytiq.bugtracker.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TicketRepository extends JpaRepository<Ticket, String> {
}