package com.example.ecom.dtos;

import com.example.ecom.models.Inventory;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CreateOrUpdateResponseDto {
    private Inventory inventory;
    private ResponseStatus responseStatus;
}
