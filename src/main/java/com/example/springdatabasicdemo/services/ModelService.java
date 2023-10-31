package com.example.springdatabasicdemo.services;

import com.example.springdatabasicdemo.dtos.ModelDto;
import com.example.springdatabasicdemo.models.Model;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface ModelService {

    ModelDto register(ModelDto model);

    void delete(String id);

    Optional<ModelDto> findModel(String id);

    List<ModelDto> getAll();

    ModelDto update(ModelDto model);
}
