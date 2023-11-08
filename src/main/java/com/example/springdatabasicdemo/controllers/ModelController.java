package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.ModelDto;
import com.example.springdatabasicdemo.services.ModelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ModelController {

    private final ModelService modelService;
    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }
    @GetMapping("/models/{startYear}")
    public ResponseEntity<List<ModelDto>> getModelFromStartYear(@PathVariable int startYear) {
        List<ModelDto> models = modelService.findModelsFromStartYear(startYear);
        return ResponseEntity.ok(models);
    }
    @GetMapping("/model/getAll")
    public ResponseEntity<List<ModelDto>> getAllModels() {
        List<ModelDto> models = modelService.getAll();
        return ResponseEntity.ok(models);
    }
    @GetMapping("/model/{id}")
    public ResponseEntity<ModelDto> findModelById(@PathVariable String id) {
        Optional<ModelDto> modelDto = modelService.findModel(id);

        return modelDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/model/create")
    public ResponseEntity<ModelDto> createModel(@RequestBody ModelDto modelDto) {
        ModelDto createdModel = modelService.register(modelDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdModel);
    }

    @PutMapping("/model/update/{id}")
    public ResponseEntity<ModelDto> updateModel(@PathVariable String id, @RequestBody ModelDto modelDto) {
        if (!id.equals(modelDto.getId())) {
            return ResponseEntity.badRequest().build();
        }

        ModelDto updatedModel = modelService.update(modelDto);
        return ResponseEntity.ok(updatedModel);
    }

    @DeleteMapping("/model/delete/{id}")
    public ResponseEntity<Void> deleteModel(@PathVariable String id) {
        modelService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
