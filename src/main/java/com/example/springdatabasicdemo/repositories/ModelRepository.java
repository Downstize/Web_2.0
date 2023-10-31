package com.example.springdatabasicdemo.repositories;

import com.example.springdatabasicdemo.models.Brand;
import com.example.springdatabasicdemo.models.Enum.CategoryEnum;
import com.example.springdatabasicdemo.models.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ModelRepository extends JpaRepository<Model, String> {
    @Query("SELECT m FROM Model m WHERE m.startYear = :startYear")
    List<Model> findModelsFromStartYear(@Param("startYear") int startYear);
}
