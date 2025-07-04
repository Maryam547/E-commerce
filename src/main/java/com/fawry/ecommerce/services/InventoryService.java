package com.fawry.ecommerce.services;

import com.fawry.ecommerce.interfaces.Product;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;


public class InventoryService {
    private Map<String, Product> products;
    
    public InventoryService() {
        this.products = new HashMap<>();
    }
    
    public void addProduct(Product product) {
        products.put(product.getName().toLowerCase(), product);
    }
    
    public Product getProduct(String name) {
        return products.get(name.toLowerCase());
    }
    
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }
    
    public List<Product> getAvailableProducts() {
        return products.values().stream()
                .filter(Product::isAvailable)
                .collect(ArrayList::new, (list, item) -> list.add(item), ArrayList::addAll);
    }
    
    public boolean isInStock(String productName, int quantity) {
        Product product = getProduct(productName);
        return product != null && product.getStock() >= quantity && product.isAvailable();
    }
    
    public void updateStock(String productName, int quantityUsed) {
        Product product = getProduct(productName);
        if (product != null) {
            product.setStock(product.getStock() - quantityUsed);
        }
    }
    
    public void displayInventory() {
        System.out.println("\n" + "=".repeat(100));
        System.out.println("                                    INVENTORY");
        System.out.println("=".repeat(100));
        System.out.printf("%-20s | %-10s | %-7s | %-12s | %s%n", 
                         "Product", "Price", "Stock", "Weight", "Details");
        System.out.println("-".repeat(100));
        
        for (Product product : products.values()) {
            System.out.println(product.getProductDetails());
        }
        System.out.println("=".repeat(100));
    }
}
