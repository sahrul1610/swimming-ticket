package com.example.swimming_ticket.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketDetailResponseDto {
    private int ticketId;
    private String customerName;
    private int price;
    private LocalDateTime purchaseDate;
}
