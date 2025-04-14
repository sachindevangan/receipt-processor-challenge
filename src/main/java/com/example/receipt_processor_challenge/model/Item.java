package com.example.receipt_processor_challenge.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Getter
@Setter
public class Item {

    @NotBlank(message = "Short description cannot be empty")
    @Pattern(regexp = "^[\\w\\s\\-]+$", message = "Invalid item description format")
    private String shortDescription;

    @NotBlank(message = "Price is required")
    @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Price must be in decimal format (xx.xx)")
    private String price;
}