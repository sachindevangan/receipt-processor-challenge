package com.example.receipt_processor_challenge.service;


import com.example.receipt_processor_challenge.model.Receipt;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class ReceiptService {

    public int calculatePoints(Receipt receipt) {
        int points = 0;

        points += receipt.getRetailer().replaceAll("[^a-zA-Z0-9]", "").length();

        double total = Double.parseDouble(receipt.getTotal());
        if (total == Math.floor(total)) points += 50;
        if (total % 0.25 == 0) points += 25;

        points += (receipt.getItems().size() / 2) * 5;

        for (var item : receipt.getItems()) {
            int length = item.getShortDescription().trim().length();
            if (length % 3 == 0) {
                points += (int) Math.ceil(Double.parseDouble(item.getPrice()) * 0.2);
            }
        }

        LocalDate date = LocalDate.parse(receipt.getPurchaseDate());
        if (date.getDayOfMonth() % 2 == 1) points += 6;

        LocalTime time = LocalTime.parse(receipt.getPurchaseTime(), DateTimeFormatter.ofPattern("HH:mm"));
        if (time.isAfter(LocalTime.of(14, 0)) && time.isBefore(LocalTime.of(16, 0))) points += 10;

        return points;
    }
}
