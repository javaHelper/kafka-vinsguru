package com.example.service;

import java.util.List;

import com.example.entity.PurchaseOrder;

public interface PurchaseOrderService {
	List<PurchaseOrder> getPurchaseOrders();

	void createPurchaseOrder(PurchaseOrder purchaseOrder);
}