package com.example.springdatabasicdemo.services;

import com.example.springdatabasicdemo.dtos.OfferDto;
import com.example.springdatabasicdemo.models.Offer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface OfferService {

    OfferDto register(OfferDto offer);

    void delete(String id);

    Optional<OfferDto> findOffer(String id);

    List<OfferDto> getAll();

    OfferDto update(OfferDto offer);
}
