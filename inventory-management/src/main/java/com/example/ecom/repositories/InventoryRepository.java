package com.example.ecom.repositories;

import com.example.ecom.models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findProductById(long productId);
    void deleteProductById(long productId);
}
