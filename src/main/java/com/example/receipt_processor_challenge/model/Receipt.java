package com.example.receipt_processor_challenge.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Receipt {

    @NotBlank(message = "Retailer cannot be empty")
    @Pattern(regexp = "^[\\w\\s&\\-'.!]+$", message = "Invalid retailer name format")
    private String retailer;


    @NotNull(message = "Purchase date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate purchaseDate;

    @NotNull(message = "Purchase time is required")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime purchaseTime;


    @Valid
    @NotEmpty(message = "Receipt must have at least one item")
    private List<Item> items;

    @NotBlank(message = "Total amount is required")
    @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Total must be in decimal format (xx.xx)")
    private String total;
}
