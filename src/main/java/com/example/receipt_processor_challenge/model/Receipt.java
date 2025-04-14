package com.example.receipt_processor_challenge.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Receipt {

    @NotBlank(message = "Retailer cannot be empty")
    @Pattern(regexp = "^[\\w\\s\\-&]+$", message = "Invalid retailer name format")
    private String retailer;

    @NotBlank(message = "Purchase date is required")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Invalid date format (YYYY-MM-DD expected)")
    private String purchaseDate;

    @NotBlank(message = "Purchase time is required")
    @Pattern(regexp = "^\\d{2}:\\d{2}$", message = "Invalid time format (HH:mm expected)")
    private String purchaseTime;

    @Valid
    @NotEmpty(message = "Receipt must have at least one item")
    private List<Item> items;

    @NotBlank(message = "Total amount is required")
    @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Total must be in decimal format (xx.xx)")
    private String total;
}
