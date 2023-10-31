package com.example.springdatabasicdemo.models.BaseEntity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class BaseEntityUUID {
    @Id
    @GeneratedValue(generator = "uuid-string")
    @GenericGenerator(name = "uuid-string",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    protected String id;


    public String getId() {
        return id;
    }
    protected void setId(String id) {
        this.id = id;
    }
}
