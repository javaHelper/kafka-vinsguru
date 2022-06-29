package com.example.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "purchase_order")
public class PurchaseOrder {
    private String id;
    private User user;
    private Product product;
    private double price;
}
