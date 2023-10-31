package com.example.springdatabasicdemo.models;

import com.example.springdatabasicdemo.models.BaseEntity.BaseEntity;
import com.example.springdatabasicdemo.models.BaseEntity.BaseEntityUUID;
import com.example.springdatabasicdemo.models.Enum.RoleEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user_role")
public class UserRole extends BaseEntityUUID {

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<User> users;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role", nullable = false)
    private RoleEnum roleEnum;

    protected UserRole() {
    }


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }
}
