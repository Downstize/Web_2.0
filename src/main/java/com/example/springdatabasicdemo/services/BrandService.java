package com.example.springdatabasicdemo.services;


import com.example.springdatabasicdemo.dtos.BrandDto;
import com.example.springdatabasicdemo.models.Brand;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface BrandService {

    BrandDto register(BrandDto brand);

    void delete(String id);

    Optional<BrandDto> findBrand(String id);

    List<BrandDto> getAll();

    BrandDto update(BrandDto brand);

}

