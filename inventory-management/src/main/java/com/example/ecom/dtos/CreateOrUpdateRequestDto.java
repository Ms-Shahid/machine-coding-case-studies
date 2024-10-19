package com.example.ecom.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CreateOrUpdateRequestDto {
    private int userId;
    private int productId;
    private int quantity;
}
