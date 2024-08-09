package com.example.swimming_ticket.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddOrEditTicketRequestDto {
    private String customerName;
    private int price;
}
