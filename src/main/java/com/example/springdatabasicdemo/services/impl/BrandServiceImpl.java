package com.example.springdatabasicdemo.services.impl;

import com.example.springdatabasicdemo.dtos.BrandDto;
import com.example.springdatabasicdemo.models.Brand;
import com.example.springdatabasicdemo.repositories.BrandRepository;
import com.example.springdatabasicdemo.services.BrandService;
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
public class BrandServiceImpl implements BrandService {

    private final ModelMapper modelMapper;
    private BrandRepository brandRepository;
    private final ValidationUtil validationUtil;

    @Autowired
    public BrandServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Autowired
    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public BrandDto register(BrandDto brand) {

        if (!this.validationUtil.isValid(brand)) {
            this.validationUtil
                    .violations(brand)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

        } else {
            try {
                Brand b = modelMapper.map(brand, Brand.class);
                if (b.getId() == null || findBrand(b.getId()).isEmpty()) {
                    return modelMapper.map(brandRepository.saveAndFlush(b), BrandDto.class);
                }
            } catch (Exception e) {
                System.out.println("Some thing went wrong!");
            }
        }
        return brand;
    }

    @Override
    public List<BrandDto> getAll() {
        return brandRepository.findAll().stream().map((s) -> modelMapper.map(s, BrandDto.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<BrandDto> findBrand(String id) {
        return Optional.ofNullable(modelMapper.map(brandRepository.findById(id), BrandDto.class));
    }

    @Override
    public void delete(String id) {
        if (brandRepository.findById(id).isPresent()) {
            brandRepository.deleteById(id);
        } else {
            throw new DataIntegrityViolationException("Exception of delete");
        }
    }

    @Override
    public BrandDto update(BrandDto brand) {
        if (brandRepository.findById(brand.getId()).isPresent()) {
            return modelMapper.map(brandRepository.save(modelMapper.map(brand, Brand.class)), BrandDto.class);
        } else {
            throw new DataIntegrityViolationException("Exception of update!");
        }
    }
}