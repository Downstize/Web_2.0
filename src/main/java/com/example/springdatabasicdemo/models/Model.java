package com.example.springdatabasicdemo.models;

import com.example.springdatabasicdemo.models.BaseEntity.BaseEntity;
import com.example.springdatabasicdemo.models.Enum.CategoryEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "model")
public class Model extends BaseEntity {

    private Brand brand;
    private List<Offer> offers;
    private String name;
    private CategoryEnum categoryEnum;
    private String imageUrl;
    private int startYear;
    private int endYear;

    protected Model() {
    }


    @ManyToOne
    @JoinColumn(name = "brand_id")
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @OneToMany(mappedBy = "model")
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

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "category", nullable = false)
    public CategoryEnum getCategoryEnum() {
        return categoryEnum;
    }

    public void setCategoryEnum(CategoryEnum categoryEnum) {
        this.categoryEnum = categoryEnum;
    }

    @Column(name = "imageurl", nullable = false ,unique = true)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(name = "startyear", nullable = false)
    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    @Column(name = "endyear", nullable = false)
    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

}
