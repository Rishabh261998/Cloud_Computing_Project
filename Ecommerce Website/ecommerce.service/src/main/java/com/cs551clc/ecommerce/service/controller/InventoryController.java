package com.cs551clc.ecommerce.service.controller;

import com.cs551clc.ecommerce.service.data.InventoryItem;
import com.cs551clc.ecommerce.service.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin(
    origins = {
        "http://localhost:3000"
    }
)

@RestController
public class InventoryController {

    @Autowired
    InventoryRepository inventoryRepository;

    @GetMapping("/inventory")
    public List<InventoryItem> getInventory() {
        return inventoryRepository.getAllItems();
    }

}
