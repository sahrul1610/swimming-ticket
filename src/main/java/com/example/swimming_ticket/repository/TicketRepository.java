package com.example.swimming_ticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.swimming_ticket.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{
    
}
