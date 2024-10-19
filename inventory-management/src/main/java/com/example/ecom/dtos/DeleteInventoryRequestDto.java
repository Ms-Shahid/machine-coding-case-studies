package com.example.ecom.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DeleteInventoryRequestDto {
    private int userId;
    private int productId;
}
