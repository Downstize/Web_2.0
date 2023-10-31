package com.example.springdatabasicdemo.init;

import com.example.springdatabasicdemo.dtos.*;
import com.example.springdatabasicdemo.models.Enum.CategoryEnum;
import com.example.springdatabasicdemo.models.Enum.EngineEnum;
import com.example.springdatabasicdemo.models.Enum.RoleEnum;
import com.example.springdatabasicdemo.models.Enum.TransmissionEnum;
import com.example.springdatabasicdemo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private ModelService modelService;
    @Autowired
    private UserService userService;
    @Autowired
    private OfferService offerService;

    private void seedData() {

        LocalDateTime created1 = LocalDateTime.of(2009, 4, 20, 7, 37, 59);
        LocalDateTime created2 = LocalDateTime.of(1999, 6, 17, 18, 41, 29);
        LocalDateTime created3 = LocalDateTime.of(1979, 1, 19, 5, 39, 12);
        LocalDateTime created4 = LocalDateTime.of(2017, 11, 21, 16, 39, 11);
        LocalDateTime modified1 = LocalDateTime.of(2012, 1, 6, 15, 12, 18);
        LocalDateTime modified2 = LocalDateTime.of(2004, 7, 28, 19, 45, 31);
        LocalDateTime modified3 = LocalDateTime.of(1983, 2, 13, 10, 13, 11);
        LocalDateTime modified4 = LocalDateTime.of(2021, 3, 14, 11, 29, 23);
        LocalDateTime userCreate1 = LocalDateTime.of(2003, 12, 14, 7, 37, 59);
        LocalDateTime userModified1 = LocalDateTime.of(2007, 7, 11, 15, 12, 18);
        LocalDateTime userCreate2 = LocalDateTime.of(2017, 3, 14, 7, 37, 59);
        LocalDateTime userModified2 = LocalDateTime.of(2019, 8, 11, 15, 12, 18);

        BrandDto brand1 = new BrandDto(null, "Renault", created1, modified1);
        BrandDto brand2 = new BrandDto(null, "BMW", created2, modified2);
        BrandDto brand3 = new BrandDto(null, "RAM", created4, modified4);
        brand1 = brandService.register(brand1);
        brand2 = brandService.register(brand2);
        brand3 = brandService.register(brand3);

        ModelDto model1 = new ModelDto(null, brand1, "Clio", CategoryEnum.Car, "https://renault-clio/wallpaper",
                2012, 2019, created1, modified1);
        ModelDto model2 = new ModelDto(null, brand2, "X5M", CategoryEnum.Car, "http://bmw-x5m/wallpaper",
                2003, 2023, created2, modified2);
        ModelDto model3 = new ModelDto(null, brand3, "1500", CategoryEnum.Truck, "http://ram-1500/wallpaper",
                1981, 1993, created3, modified3);
        ModelDto model4 = new ModelDto(null, brand2, "R 1250 RS", CategoryEnum.Motorcycle, "http://bmw-r-1250-rs/wallpaper",
                2019, 2023, created4, modified4);
        model1 = modelService.register(model1);
        model2 = modelService.register(model2);
        model3 = modelService.register(model3);
        model4 = modelService.register(model4);

        UserRoleDto role1 = new UserRoleDto(null, RoleEnum.Admin);
        UserRoleDto role2 = new UserRoleDto(null, RoleEnum.User);
        role1 = userRoleService.register(role1);
        role2 = userRoleService.register(role2);

        UserDto user1 = new UserDto(null, role1, "Venceslao", "3961598qw", "Venceslao",
                "Terekhov", true, "http://venceslao-terekhov/wallpaper", userCreate1, userModified1);
        UserDto user2 = new UserDto(null, role2, "GymBoss", "iLikeGym", "Alex",
                "Brendika", false, "http://alex-brendika/wallpaper", userCreate2, userModified2);
        user1 = userService.register(user1);
        user2 = userService.register(user2);

        BigDecimal price1 = new BigDecimal(987465);
        BigDecimal price2 = new BigDecimal(8297487);
        BigDecimal price3 = new BigDecimal(293892);
        BigDecimal price4 = new BigDecimal(48938);

        OfferDto offer1 = new OfferDto(null, model1, user2, "Basic car for people with love", EngineEnum.GASOLINE,
                "https://renault-clio/offer.pdf", 203, price4, TransmissionEnum.MANUAL, 2019, created1, modified1, "PANAUTO");
        OfferDto offer2 = new OfferDto(null, model2, user1, "Family car but with some additions", EngineEnum.GASOLINE,
                "http://bmw-x5m/offer.pdf", 17, price2, TransmissionEnum.AUTOMATIC, 2023, created2, modified2, "AVILON");
        OfferDto offer3 = new OfferDto(null, model3, user2, "Car for rally", EngineEnum.DIESEL,
                "http://ram-1500/offer.pdf", 3002, price3, TransmissionEnum.MANUAL, 1992, created3, modified3, "MAJOR");
        OfferDto offer4 = new OfferDto(null, model4, user1, "The most comfortable motorcycle for adventures", EngineEnum.GASOLINE,
                "http://bmw-r-1250-rs/offer.pdf", 193, price1, TransmissionEnum.MANUAL, 2022, created4, modified4, "MAJOR AUTO");
        offerService.register(offer1);
        offerService.register(offer2);
        offerService.register(offer3);
        offerService.register(offer4);
    }

//    private void deleteData(){
//        brandService.delete(1L);
//        modelService.delete(1L);
//        //offerService.delete(1L);
//        userService.delete(1L);
//        userRoleService.delete(1L);
//    }


    @Override
    public void run(String... args){
        seedData();
       // deleteData();
    }
}