package com.example.ecom.models;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
public class Product extends BaseModel{
    private String name;
    private String description;
    private double price;
}
