package com.fawry.ecommerce.utils;

import com.fawry.ecommerce.models.Books;
import com.fawry.ecommerce.models.Clothing;
import com.fawry.ecommerce.models.Electronics;
import com.fawry.ecommerce.services.InventoryService;

import java.time.LocalDate;


public class DataInitializer {
    
    public static void initializeInventory(InventoryService inventory) {
        // Electronics
        inventory.addProduct(new Electronics("Laptop", 999.99, 10, 2.5, "Dell", 24));
        inventory.addProduct(new Electronics("Smartphone", 699.99, 15, 0.2, "Samsung", 12));
        inventory.addProduct(new Electronics("Tablet", 399.99, 8, 0.5, "Apple", 12));
        inventory.addProduct(new Electronics("Headphones", 199.99, 20, 0.3, "Sony", 6));
        
        // Clothing
        inventory.addProduct(new Clothing("T-Shirt", 29.99, 50, 0.2, "L", "Cotton"));
        inventory.addProduct(new Clothing("Jeans", 79.99, 30, 0.8, "M", "Denim"));
        inventory.addProduct(new Clothing("Jacket", 149.99, 15, 1.2, "XL", "Polyester"));
        inventory.addProduct(new Clothing("Sneakers", 119.99, 25, 0.9, "42", "Leather"));
        
        // Books (some with near expiry for testing)
        inventory.addProduct(new Books("Java Programming", 49.99, 100, 0.8, "Oracle Press", "978-0134685991", LocalDate.now().plusMonths(6)));
        inventory.addProduct(new Books("Design Patterns", 59.99, 75, 0.9, "Gang of Four", "978-0201633612", LocalDate.now().plusMonths(12)));
        inventory.addProduct(new Books("Clean Code", 44.99, 120, 0.7, "Robert Martin", "978-0132350884", LocalDate.now().plusDays(15))); // Expires soon
        inventory.addProduct(new Books("Effective Java", 54.99, 80, 0.8, "Joshua Bloch", "978-0134685991", LocalDate.now().minusDays(5))); // Expired
        
        System.out.println("Inventory initialized with sample products!");
    }
}
