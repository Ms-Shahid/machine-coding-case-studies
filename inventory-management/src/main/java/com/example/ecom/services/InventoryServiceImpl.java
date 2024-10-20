package com.example.ecom.services;

import com.example.ecom.exceptions.ProductNotFoundException;
import com.example.ecom.exceptions.UnAuthorizedAccessException;
import com.example.ecom.exceptions.UserNotFoundException;
import com.example.ecom.models.Inventory;
import com.example.ecom.models.Product;
import com.example.ecom.models.User;
import com.example.ecom.models.UserType;
import com.example.ecom.repositories.InventoryRepository;
import com.example.ecom.repositories.ProductRepository;
import com.example.ecom.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService{

    private InventoryRepository inventoryRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository, ProductRepository productRepository, UserRepository userRepository){
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Inventory createOrUpdateInventory(int userId, int productId, int quantity) throws ProductNotFoundException, UserNotFoundException, UnAuthorizedAccessException {

        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isEmpty()){
            throw new UserNotFoundException("User with id " + userId + " not found");
        }
        User user = userOpt.get();
        if( user.getUserType() == UserType.CUSTOMER){
            throw new UnAuthorizedAccessException("Customer cannot create/update");
        }

        Optional<Inventory> inventoryOptional = inventoryRepository.findProductById(productId);
        if(inventoryOptional.isEmpty()){

            Product product = this.productRepository.findProductById(productId).orElseThrow(() -> new ProductNotFoundException("Product Not found"));
            Inventory inventory = new Inventory();
            inventory.setProduct(product);
            inventory.setQuantity(inventory.getQuantity() + quantity);
            return inventoryRepository.save(inventory);
        }else {
            Inventory inventory = inventoryOptional.get();
            inventory.setQuantity(inventory.getQuantity() + quantity);
            return inventoryRepository.save(inventory);
        }

    }

    @Override
    public void deleteInventory(int userId, int productId) throws UserNotFoundException, UnAuthorizedAccessException {

        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isEmpty()){
            throw new UserNotFoundException("User not found");
        }
        User user = userOpt.get();
        if( user.getUserType() == UserType.CUSTOMER){
            throw new UnAuthorizedAccessException("Customer cannot create/update");
        }
        inventoryRepository.deleteProductById(productId);
    }
}
