package com.example.swimming_ticket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.swimming_ticket.dto.request.AddOrEditTicketRequestDto;
import com.example.swimming_ticket.dto.response.MessageResponse;
import com.example.swimming_ticket.dto.response.TicketDetailResponseDto;
import com.example.swimming_ticket.dto.response.TicketListResponseDto;
import com.example.swimming_ticket.services.TicketService;


@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    
    @Autowired
    private TicketService ticketService;

    @GetMapping
    public ResponseEntity<TicketListResponseDto> getAllTickets(){
        return ticketService.getAllTickets();
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketDetailResponseDto> getTicketById(@PathVariable int ticketId ){
        return ticketService.getTicketDetail(ticketId);
    }

    @PostMapping("/add")
    public ResponseEntity<MessageResponse> addTicket(@RequestBody AddOrEditTicketRequestDto addOrEditTicketRequestDto){
        return ticketService.addTicket(addOrEditTicketRequestDto);
    }

    @PutMapping("/{ticketId}")
    public ResponseEntity<MessageResponse> editTicket(@RequestBody AddOrEditTicketRequestDto addOrEditTicketRequestDto, @PathVariable int ticketId){
        return ticketService.editTicket(addOrEditTicketRequestDto, ticketId);
    }

    @DeleteMapping("/delete/{ticketId}")
    public ResponseEntity<MessageResponse> deleteTicket(@PathVariable int ticketId){
        return ticketService.deleteTicket(ticketId);
    }
}
