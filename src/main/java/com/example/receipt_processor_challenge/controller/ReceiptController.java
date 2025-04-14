package com.example.receipt_processor_challenge.controller;

import com.example.receipt_processor_challenge.model.Receipt;
import com.example.receipt_processor_challenge.repository.ReceiptRepository;
import com.example.receipt_processor_challenge.service.ReceiptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {
    private final ReceiptRepository repository;
    private final ReceiptService service;

    public ReceiptController(ReceiptRepository repository, ReceiptService service) {
        this.repository = repository;
        this.service = service;
    }

    @PostMapping("/process")
    public ResponseEntity<Map<String, String>> processReceipt(@RequestBody Receipt receipt) {
        String id = repository.storeReceipt(receipt);
        return ResponseEntity.ok(Map.of("id", id));
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<?> getPoints(@PathVariable String id) {
        Receipt receipt = repository.getReceipt(id);
        if (receipt == null) {
            return ResponseEntity.status(404).body(Map.of("error", "No receipt found for that ID."));
        }
        int points = service.calculatePoints(receipt);
        return ResponseEntity.ok(Map.of("points", points));
    }

    @GetMapping
    public ResponseEntity<Map<String, Receipt>> getAllReceipts() {
        return ResponseEntity.ok(repository.getAllReceipts());
    }
}
