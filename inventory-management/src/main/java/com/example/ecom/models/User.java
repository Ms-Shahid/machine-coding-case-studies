package com.example.ecom.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@Table(name = "`user`")
public class User extends BaseModel{
    private String name;
    private String email;
    private UserType userType;
}
