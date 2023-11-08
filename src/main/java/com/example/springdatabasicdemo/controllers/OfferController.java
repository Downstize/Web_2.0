package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.OfferDto;
import com.example.springdatabasicdemo.services.OfferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class OfferController {

    private final OfferService offerService;
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/offer/getAll")
    public ResponseEntity<List<OfferDto>> getAllOffers() {
        List<OfferDto> offers = offerService.getAll();
        return ResponseEntity.ok(offers);
    }

    @GetMapping("/offer/{id}")
    public ResponseEntity<OfferDto> findOfferById(@PathVariable String id) {
        Optional<OfferDto> offerDto = offerService.findOffer(id);

        return offerDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/offer/create")
    public ResponseEntity<OfferDto> createOffer(@RequestBody OfferDto offerDto) {
        OfferDto createdOffer = offerService.register(offerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOffer);
    }

    @PutMapping("/offer/update/{id}")
    public ResponseEntity<OfferDto> updateOffer(@PathVariable String id, @RequestBody OfferDto offerDto) {
        if (!id.equals(offerDto.getId())) {
            return ResponseEntity.badRequest().build();
        }

        OfferDto updatedOffer = offerService.update(offerDto);
        return ResponseEntity.ok(updatedOffer);
    }

    @DeleteMapping("/offer/delete/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable String id) {
        offerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
