package com.example.receipt_processor_challenge.service;


import com.example.receipt_processor_challenge.model.Receipt;
import com.example.receipt_processor_challenge.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

@Service
public class ReceiptService {

    private final ReceiptRepository repository;

    @Autowired
    public ReceiptService(ReceiptRepository repository) {
        this.repository = repository;
    }

    public int calculatePoints(String receiptId) {
        if (!repository.exists(receiptId)) {
            throw new IllegalArgumentException("Receipt ID not found: " + receiptId); // Ideally, use a custom exception
        }

        Receipt receipt = repository.getReceipt(receiptId);
        int points = 0;

        points += receipt.getRetailer().trim().replaceAll("[^a-zA-Z0-9]", "").length();

        double total = Double.parseDouble(receipt.getTotal());
        if (total == Math.floor(total)) points += 50;
        if (total % 0.25 == 0) points += 25;

        points += (receipt.getItems().size() / 2) * 5;

        for (var item : receipt.getItems()) {
            String desc = item.getShortDescription().trim();
            int length = desc.length();
            System.out.println("Item: '" + desc + "', Length: " + length);

            if (length % 3 == 0) {
                double price = Double.parseDouble(item.getPrice());
                int bonus = (int) Math.ceil(price * 0.2);
                System.out.println(">> Bonus awarded: " + bonus + " points for item '" + desc + "'");
                points += bonus;
            } else {
                System.out.println(">> No bonus for item '" + desc + "'");
            }
        }


        LocalDate date = receipt.getPurchaseDate();
        if (date.getDayOfMonth() % 2 == 1) points += 6;

        LocalTime time = receipt.getPurchaseTime();
        if (!time.isBefore(LocalTime.of(14, 0)) && time.isBefore(LocalTime.of(16, 0))) {
            points += 10;
        }

        return points;
    }

    public String saveReceipt(Receipt receipt) {
        return repository.storeReceipt(receipt);
    }

    public Map<String, Receipt> getAllReceipts() {
        return repository.getAllReceipts();
    }

}
