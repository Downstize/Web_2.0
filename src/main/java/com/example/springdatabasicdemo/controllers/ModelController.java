package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.ModelDto;
import com.example.springdatabasicdemo.services.ModelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ModelController {

    private final ModelService modelService;
    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }
    @GetMapping("models/{startYear}")
    public ResponseEntity<List<ModelDto>> getModelFromStartYear(@PathVariable int startYear) {
        List<ModelDto> models = modelService.findModelsFromStartYear(startYear);
        return ResponseEntity.ok(models);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ModelDto> findModelById(@PathVariable String id) {
        Optional<ModelDto> modelDto = modelService.findModel(id);

        if (modelDto.isPresent()) {
            return ResponseEntity.ok(modelDto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
