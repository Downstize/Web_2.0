package com.example.springdatabasicdemo.models;

import com.example.springdatabasicdemo.models.BaseEntity.BaseEntity;
import com.example.springdatabasicdemo.models.Enum.CategoryEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "model")
public class Model extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;
    @OneToMany(mappedBy = "model")
    private List<Offer> offers;

    private String name;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "category", nullable = false)
    private CategoryEnum categoryEnum;
    @Column(name = "imageurl", nullable = false ,unique = true)
    private String imageUrl;
    @Column(name = "startyear", nullable = false)
    private int startYear;
    @Column(name = "endyear", nullable = false)
    private int endYear;

    protected Model() {
    }


    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }


    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    @Column(name = "model_name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public CategoryEnum getCategoryEnum() {
        return categoryEnum;
    }

    public void setCategoryEnum(CategoryEnum categoryEnum) {
        this.categoryEnum = categoryEnum;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }


    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

}
