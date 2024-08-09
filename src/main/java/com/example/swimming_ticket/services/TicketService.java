package com.example.swimming_ticket.services;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.swimming_ticket.dto.request.AddOrEditTicketRequestDto;
import com.example.swimming_ticket.dto.response.MessageResponse;
import com.example.swimming_ticket.dto.response.TicketDetailResponseDto;
import com.example.swimming_ticket.dto.response.TicketListResponse;
import com.example.swimming_ticket.dto.response.TicketListResponseDto;
import com.example.swimming_ticket.model.Ticket;
import com.example.swimming_ticket.repository.TicketRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TicketService {
    
    @Autowired
    private TicketRepository ticketRepository;

    public ResponseEntity<TicketListResponseDto> getAllTickets(){
        try {
            List<Ticket> tickets = ticketRepository.findAll();

            List<TicketListResponse> ticketListResponses = tickets.stream().map(ticket -> {
                
                return new TicketListResponse(
                    ticket.getTicketId(),
                    ticket.getCustomerName(),
                    ticket.getPrice()
                );
            }).collect(Collectors.toList());

            TicketListResponseDto ticketListResponseDto = new TicketListResponseDto();
            ticketListResponseDto.setTicketList(ticketListResponses);

            return ResponseEntity.ok(ticketListResponseDto);
        } catch (Exception e) {
            log.error(null, e);
            return ResponseEntity.internalServerError().body(new TicketListResponseDto(Collections.emptyList()));
        }
        
    }

    public ResponseEntity<TicketDetailResponseDto> getTicketDetail(int ticketId){
        try {
            Optional<Ticket> ticketOptional = ticketRepository.findById(ticketId);
            if(ticketOptional.isPresent()){
                Ticket ticket = ticketOptional.get();

                TicketDetailResponseDto ticketDetailResponseDto = TicketDetailResponseDto.builder()
                    .ticketId(ticket.getTicketId())
                    .customerName(ticket.getCustomerName())
                    .price(ticket.getPrice())
                    .purchaseDate(ticket.getPurchaseDate())
                    .build();

                    return ResponseEntity.ok(ticketDetailResponseDto);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching ticket details", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    public ResponseEntity<MessageResponse> addTicket(AddOrEditTicketRequestDto addTicketRequestDto){
        try {
            Ticket ticket = new Ticket();
            ticket.setCustomerName(addTicketRequestDto.getCustomerName());
            ticket.setPrice(addTicketRequestDto.getPrice());
            ticket.setPurchaseDate(LocalDateTime.now());

            ticketRepository.save(ticket);
            return ResponseEntity.ok().body(new MessageResponse(HttpStatus.CREATED.value(), "Ticket berhasil ditambahkan"));
        } catch (Exception e) {
            log.error("Terjadi kesalahan saat membuat ticket", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Terjadi kesalahan saat membuat ticket: " + e.getMessage()));
        }
    }

    public ResponseEntity<MessageResponse> editTicket(AddOrEditTicketRequestDto editTicketRequestDto, int ticketId){
        try {
            Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);

            if(optionalTicket.isPresent()){
                Ticket ticket = optionalTicket.get();
                ticket.setCustomerName(editTicketRequestDto.getCustomerName());
                ticket.setPrice(editTicketRequestDto.getPrice());

                ticketRepository.save(ticket);
                return ResponseEntity.ok().body(new MessageResponse(HttpStatus.CREATED.value(), "Ticket berhasil diedit"));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Gagal memperbarui data ticket:", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Gagal memperbarui data ticket: " + e.getMessage()));
        }
    }

    public ResponseEntity<MessageResponse> deleteTicket(int ticketId){
        try {
            Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);

            if(optionalTicket.isPresent()){
                ticketRepository.deleteById(ticketId);
                return ResponseEntity.ok().body(new MessageResponse(HttpStatus.CREATED.value(), "Ticket berhasil dihapus"));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Gagal menghapus data ticket:", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Gagal menghapus data ticket: " + e.getMessage()));
        }
    }
}
