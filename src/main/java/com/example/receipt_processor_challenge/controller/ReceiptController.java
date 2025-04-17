package com.example.receipt_processor_challenge.controller;

import com.example.receipt_processor_challenge.model.Receipt;
import com.example.receipt_processor_challenge.repository.ReceiptRepository;
import com.example.receipt_processor_challenge.service.ReceiptService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Map<String, String>> processReceipt(@Valid @RequestBody Receipt receipt) {
        String id = service.saveReceipt(receipt);
        return ResponseEntity.ok(Map.of("id", id));
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<Map<String, Integer>> getPoints(@PathVariable String id) {
        int points = service.calculatePoints(id);
        return ResponseEntity.ok(Map.of("points", points));
    }

    @GetMapping
    public ResponseEntity<Map<String, Receipt>> getAllReceipts() {
        return ResponseEntity.ok(service.getAllReceipts());
    }

}
