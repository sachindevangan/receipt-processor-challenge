package com.example.receipt_processor_challenge.repository;


import com.example.receipt_processor_challenge.model.Receipt;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ReceiptRepository {
    private final Map<String, Receipt> receiptStore = new ConcurrentHashMap<>();

    public String storeReceipt(Receipt receipt) {
        String id = UUID.randomUUID().toString();
        receiptStore.put(id, receipt);
        return id;
    }

    public Receipt getReceipt(String id) {
        return receiptStore.get(id);
    }

    public boolean exists(String id) {
        return receiptStore.containsKey(id);
    }

    public Map<String, Receipt> getAllReceipts() {
        return receiptStore;
    }
}
