package com.example.springdatabasicdemo.repositories;


import com.example.springdatabasicdemo.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface OfferRepository extends JpaRepository<Offer, String> {
}
