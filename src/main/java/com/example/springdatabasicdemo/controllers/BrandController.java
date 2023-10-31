package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.BrandDto;
import com.example.springdatabasicdemo.services.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BrandController {

    private final BrandService brandService;
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }
    @GetMapping("/brand/getAll")
    public ResponseEntity<List<BrandDto>> getAllBrands() {
        List<BrandDto> brands = brandService.getAll();
        return ResponseEntity.ok(brands);
    }

    @GetMapping("/brand/{id}")
    public ResponseEntity<BrandDto> getBrand(@PathVariable String id) {
        Optional<BrandDto> brand = brandService.findBrand(id);
        return brand.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/brand/create")
    public ResponseEntity<BrandDto> createBrand(@RequestBody BrandDto brandDto) {
        BrandDto createdBrand = brandService.register(brandDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBrand);
    }

    @PutMapping("/brand/update/{id}")
    public ResponseEntity<BrandDto> updateBrand(@PathVariable String id, @RequestBody BrandDto brandDto) {
        if (!id.equals(brandDto.getId())) {
            return ResponseEntity.badRequest().build();
        }

        BrandDto updatedBrand = brandService.update(brandDto);
        return ResponseEntity.ok(updatedBrand);
    }

    @DeleteMapping("/brand/delete/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable String id) {
        brandService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
