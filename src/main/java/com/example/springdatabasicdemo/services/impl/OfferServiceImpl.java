package com.example.springdatabasicdemo.services.impl;

import com.example.springdatabasicdemo.dtos.OfferDto;
import com.example.springdatabasicdemo.models.Offer;
import com.example.springdatabasicdemo.repositories.OfferRepository;
import com.example.springdatabasicdemo.services.OfferService;
import com.example.springdatabasicdemo.utill.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private ModelMapper modelMapper;
    private OfferRepository offerRepository;
    private  ValidationUtil validationUtil;

    @Autowired
    public void setValidationUtil(ValidationUtil validationUtil) {
        this.validationUtil = validationUtil;
    }
    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Autowired
    public void setOfferRepository(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public OfferDto register(OfferDto offer) {

        if (!this.validationUtil.isValid(offer)) {
            this.validationUtil
                    .violations(offer)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

        } else{
            try{
                Offer b = modelMapper.map(offer, Offer.class);
            if (b.getId() == null || findOffer(b.getId()).isEmpty()) {
                return modelMapper.map(offerRepository.save(b), OfferDto.class);
            }
            } catch (Exception e) {
                System.out.println("Smth goes wrong!");
        }
        }
        return offer;
    }

    @Override
    public List<OfferDto> getAll() {
        return offerRepository.findAll().stream().map((s) -> modelMapper.map(s, OfferDto.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<OfferDto> findOffer(String id) {
        return Optional.ofNullable(modelMapper.map(offerRepository.findById(id), OfferDto.class));
    }

    @Override
    public void delete(String id) {
        if (offerRepository.findById(id).isPresent()) {
            offerRepository.deleteById(id);
        } else {
            throw new DataIntegrityViolationException("Exception of delete");
        }
    }

    @Override
    public OfferDto update(OfferDto offer) {
        if (offerRepository.findById(offer.getId()).isPresent()) {
            return modelMapper.map(offerRepository.save(modelMapper.map(offer, Offer.class)), OfferDto.class);
        } else {
            throw new DataIntegrityViolationException("Exception of update");
        }
    }
}