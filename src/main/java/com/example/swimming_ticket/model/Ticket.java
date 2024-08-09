package com.example.swimming_ticket.model;

import java.time.LocalDateTime;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ticket")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Ticket {
    @Id
    @Column(name = "ticket_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ticketId;

    @Column(name="customer_name")
    private String customerName;

    @Column(name="price")
    private int price;

    @Column(name="purchase_date")
    private LocalDateTime purchaseDate;
}

