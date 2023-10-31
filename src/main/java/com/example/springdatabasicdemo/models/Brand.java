package com.example.springdatabasicdemo.models;

import com.example.springdatabasicdemo.models.BaseEntity.BaseEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "brand")
public class Brand extends BaseEntity {

    private List<Model> models;
    private String name;

    protected Brand() {
    }


    @OneToMany(mappedBy = "brand")
    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    @Column(name = "brand_name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
