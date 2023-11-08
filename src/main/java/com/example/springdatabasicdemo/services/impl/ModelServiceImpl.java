package com.example.springdatabasicdemo.services.impl;

import com.example.springdatabasicdemo.dtos.ModelDto;
import com.example.springdatabasicdemo.models.Model;
import com.example.springdatabasicdemo.repositories.ModelRepository;
import com.example.springdatabasicdemo.services.ModelService;
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
public class ModelServiceImpl implements ModelService {

    private  ModelMapper modelMapper;
    private ModelRepository modelRepository;
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
    public void setModelRepository(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public ModelDto register(ModelDto model) {

        if (!this.validationUtil.isValid(model)) {
            this.validationUtil
                    .violations(model)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

        } else {
            try {
                Model b = modelMapper.map(model, Model.class);
                if (b.getId() == null || findModel(b.getId()).isEmpty()) {
                    return modelMapper.map(modelRepository.saveAndFlush(b), ModelDto.class);
                }
            } catch (Exception e) {
                System.out.println("Smth goes wrong!");
            }
        }
        return model;
    }

    @Override
    public List<ModelDto> getAll() {
        return modelRepository.findAll().stream().map((s) -> modelMapper.map(s, ModelDto.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<ModelDto> findModel(String id) {
        return Optional.ofNullable(modelMapper.map(modelRepository.findById(id), ModelDto.class));
    }

    @Override
    public void delete(String id) {
        if (modelRepository.findById(id).isPresent()) {
            modelRepository.deleteById(id);
        } else {
            throw new DataIntegrityViolationException("Exception of delete");
        }
    }

    @Override
    public ModelDto update(ModelDto model) {
        if (modelRepository.findById(model.getId()).isPresent()) {
            return modelMapper.map(modelRepository.save(modelMapper.map(model, Model.class)), ModelDto.class);
        } else {
            throw new DataIntegrityViolationException("Exception of update!");
        }
    }

    @Override
    public List<ModelDto> findModelsFromStartYear(int startYear) {
        return modelRepository.findModelsFromStartYear(startYear).stream()
                .map(model -> modelMapper.map(model, ModelDto.class))
                .collect(Collectors.toList());
    }

}