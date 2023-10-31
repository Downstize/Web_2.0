package com.example.springdatabasicdemo.models;

import com.example.springdatabasicdemo.models.BaseEntity.BaseEntity;
import com.example.springdatabasicdemo.models.Enum.EngineEnum;
import com.example.springdatabasicdemo.models.Enum.TransmissionEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "offer")
public class Offer extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name = "description", nullable = false ,unique = true)
    private String description;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "engine", nullable = false)
    private EngineEnum engineEnum;
    @Column(name = "imagine_url", nullable = false ,unique = true)
    private String imageUrl;
    @Column(name = "mileage", nullable = false)
    private int mileage;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "transmission", nullable = false)
    private TransmissionEnum transmissionEnum;
    @Column(name = "year", nullable = false)
    private int year;
    @Column(name = "seller", nullable = false)
    private String seller;

    protected Offer() {
    }


    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }


    public User getUser(){return user;}

    public void setUser(User user){ this.user = user;}


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public EngineEnum getEngineEnum() {
        return engineEnum;
    }

    public void setEngineEnum(EngineEnum engineEnum) {
        this.engineEnum = engineEnum;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public TransmissionEnum getTransmissionEnum() {
        return transmissionEnum;
    }

    public void setTransmissionEnum(TransmissionEnum transmissionEnum) {
        this.transmissionEnum = transmissionEnum;
    }


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}