package com.cs551clc.ecommerce.service.service;

import com.cs551clc.ecommerce.service.data.dynamodb.InventoryItem;
import com.cs551clc.ecommerce.service.repository.InventoryRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository repository;


    public InventoryItem getItemById(String productKey) throws Exception {
        try {
            return repository.getItemByProductKey(productKey).get(0);
        }
        catch (Exception e) {
            throw new BadRequestException("Unable to fetch inventory item with key: "+productKey+" Reason: "+e.getMessage());
        }
    }

    public void transactItem(String productKey, BigInteger qty) throws Exception {
        InventoryItem item = getItemById(productKey);
        BigInteger qty_available = item.getQty_available();
        item.setQty_available(qty_available.subtract(qty));
        repository.saveItem(item);
    }
}
