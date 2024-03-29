package com.example.springdatabasicdemo.dtos;

import com.example.springdatabasicdemo.models.Enum.EngineEnum;
import com.example.springdatabasicdemo.models.Enum.TransmissionEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class OfferDto {
    private String id;
    private ModelDto model;
    private UserDto user;
    private String description;
    private EngineEnum engineEnum;
    private String imageUrl;
    private int mileage;
    private BigDecimal price;
    private TransmissionEnum transmissionEnum;
    private int year;
    private LocalDateTime created;
    private LocalDateTime modified;
    private String seller;

    protected OfferDto() {};

    public OfferDto(String id, ModelDto model, UserDto user, String description, EngineEnum engineEnum, String imageUrl,
                    int mileage, BigDecimal price, TransmissionEnum transmissionEnum, int year, LocalDateTime created,
                    LocalDateTime modified, String seller) {
        this.id = id;
        this.model = model;
        this.user = user;
        this.description = description;
        this.engineEnum = engineEnum;
        this.imageUrl = imageUrl;
        this.mileage = mileage;
        this.price = price;
        this.transmissionEnum = transmissionEnum;
        this.year = year;
        this.created = created;
        this.modified = modified;
        this.seller = seller;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ModelDto getModel() {
        return model;
    }

    public void setModel(ModelDto model) {
        this.model = model;
    }

    public UserDto getUsers() {
        return user;
    }

    public void setUsers(UserDto user) {
        this.user = user;
    }

    @NotNull
    @NotEmpty
    @Length(min = 7, message = "Year must be minimum seven characters!")
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

    @NotNull
    @NotEmpty
    @Length(min = 10, message = "Image URL must be minimum two characters!")
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

    @DecimalMin(value = "100")
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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    @NotNull
    @NotEmpty
    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

}