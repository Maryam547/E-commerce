package com.fawry.ecommerce.services;

import com.fawry.ecommerce.models.Product;
import com.fawry.ecommerce.models.CartItem;
import java.util.ArrayList;
import java.util.List;


public class ShoppingCartService implements CartService {
    private List<CartItem> items;
    private InventoryService inventoryService;
    
    public ShoppingCartService(InventoryService inventoryService) {
        this.items = new ArrayList<>();
        this.inventoryService = inventoryService;
    }
    
    @Override
    public void addItem(String productName, int quantity) {
        Product product = inventoryService.getProduct(productName);
        
        if (product == null) {
            System.out.println("Error: Product '" + productName + "' not found!");
            return;
        }
        
        if (!inventoryService.isInStock(productName, quantity)) {
            System.out.println("Error: Insufficient stock for '" + productName + "'!");
            System.out.println("Available stock: " + product.getStock());
            return;
        }
        
        // Check if item already exists in cart
        CartItem existingItem = findCartItem(productName);
        if (existingItem != null) {
            int newQuantity = existingItem.getQuantity() + quantity;
            if (inventoryService.isInStock(productName, newQuantity)) {
                existingItem.setQuantity(newQuantity);
                System.out.println("Updated quantity for '" + productName + "' to " + newQuantity);
            } else {
                System.out.println("Error: Cannot add more. Total would exceed available stock!");
            }
        } else {
            items.add(new CartItem(product, quantity));
            System.out.println("Added " + quantity + " x '" + productName + "' to cart");
        }
    }
    
    @Override
    public void removeItem(String productName) {
        CartItem item = findCartItem(productName);
        if (item != null) {
            items.remove(item);
            System.out.println("Removed '" + productName + "' from cart");
        } else {
            System.out.println("Error: '" + productName + "' not found in cart!");
        }
    }
    
    @Override
    public void updateQuantity(String productName, int quantity) {
        if (quantity <= 0) {
            removeItem(productName);
            return;
        }
        
        CartItem item = findCartItem(productName);
        if (item != null) {
            if (inventoryService.isInStock(productName, quantity)) {
                item.setQuantity(quantity);
                System.out.println("Updated quantity for '" + productName + "' to " + quantity);
            } else {
                System.out.println("Error: Insufficient stock for requested quantity!");
            }
        } else {
            System.out.println("Error: '" + productName + "' not found in cart!");
        }
    }
    
    @Override
    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }
    
    @Override
    public double getTotalPrice() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }
    
    @Override
    public double getTotalWeight() {
        return items.stream().mapToDouble(CartItem::getTotalWeight).sum();
    }
    
    @Override
    public void clearCart() {
        items.clear();
        System.out.println("Cart cleared!");
    }
    
    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    @Override
    public void displayCart() {
        if (isEmpty()) {
            System.out.println("\nYour cart is empty!");
            return;
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                    SHOPPING CART");
        System.out.println("=".repeat(60));
        
        for (CartItem item : items) {
            System.out.println(item);
        }
        
        System.out.println("-".repeat(60));
        System.out.printf("Total Weight: %.2f kg%n", getTotalWeight());
        System.out.printf("Total Price: $%.2f%n", getTotalPrice());
        System.out.println("=".repeat(60));
    }
    
    private CartItem findCartItem(String productName) {
        return items.stream()
                .filter(item -> item.getProduct().getName().equalsIgnoreCase(productName))
                .findFirst()
                .orElse(null);
    }
}
