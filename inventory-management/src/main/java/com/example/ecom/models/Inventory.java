package com.example.ecom.models;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
public class Inventory extends BaseModel{
    private Product product;
    private int quantity;
}
