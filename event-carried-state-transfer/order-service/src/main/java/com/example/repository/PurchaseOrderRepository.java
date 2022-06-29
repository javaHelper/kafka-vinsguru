package com.example.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.PurchaseOrder;

@Repository
public interface PurchaseOrderRepository extends MongoRepository<PurchaseOrder, ObjectId> {
	@Query(value =  "{ 'user.id': ?0 }")
    List<PurchaseOrder> findByUserId(Long userId);
}