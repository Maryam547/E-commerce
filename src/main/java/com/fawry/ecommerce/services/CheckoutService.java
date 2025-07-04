package com.fawry.ecommerce.services;

import com.fawry.ecommerce.interfaces.CartService;
import com.fawry.ecommerce.interfaces.DiscountStrategy;
import com.fawry.ecommerce.interfaces.ShippingCalculator;
import com.fawry.ecommerce.models.CartItem;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class CheckoutService {
    private CartService cartService;
    private InventoryService inventoryService;
    private List<DiscountStrategy> discountStrategies;
    private ShippingCalculator shippingCalculator;
    
    public CheckoutService(CartService cartService, InventoryService inventoryService) {
        this.cartService = cartService;
        this.inventoryService = inventoryService;
        this.discountStrategies = new ArrayList<>();
    }
    
    public void addDiscountStrategy(DiscountStrategy strategy) {
        discountStrategies.add(strategy);
    }
    
    public void setShippingCalculator(ShippingCalculator calculator) {
        this.shippingCalculator = calculator;
    }
    
    public void processCheckout(String customerName, String address) {
        if (cartService.isEmpty()) {
            System.out.println("Cannot checkout: Cart is empty!");
            return;
        }
        
        if (shippingCalculator == null) {
            System.out.println("Error: No shipping method selected!");
            return;
        }
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("                           PROCESSING CHECKOUT");
        System.out.println("=".repeat(80));
        
        // Calculate totals
        double subtotal = cartService.getTotalPrice();
        double totalDiscount = calculateTotalDiscount();
        double discountedTotal = subtotal - totalDiscount;
        double shippingCost = shippingCalculator.calculateShipping(cartService.getTotalWeight(), address);
        double finalTotal = discountedTotal + shippingCost;
        
        // Update inventory
        updateInventoryAfterPurchase();
        
        // Print receipt
        printReceipt(customerName, address, subtotal, totalDiscount, shippingCost, finalTotal);
        
        // Clear cart
        cartService.clearCart();
        
        System.out.println("Checkout completed successfully!");
        System.out.println("Thank you for your purchase, " + customerName + "!");
    }
    
    private double calculateTotalDiscount() {
        double totalDiscount = 0;
        
        for (CartItem item : cartService.getItems()) {
            for (DiscountStrategy strategy : discountStrategies) {
                if (strategy.isApplicable(item)) {
                    double discount = strategy.calculateDiscount(item);
                    totalDiscount += discount;
                    System.out.println("Applied: " + strategy.getDiscountDescription() + 
                                     " - Saved $" + String.format("%.2f", discount));
                }
            }
        }
        
        return totalDiscount;
    }
    
    private void updateInventoryAfterPurchase() {
        for (CartItem item : cartService.getItems()) {
            inventoryService.updateStock(item.getProduct().getName(), item.getQuantity());
        }
    }
    
    private void printReceipt(String customerName, String address, double subtotal, 
                            double totalDiscount, double shippingCost, double finalTotal) {
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("                              RECEIPT");
        System.out.println("=".repeat(80));
        System.out.println("Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("Customer: " + customerName);
        System.out.println("Address: " + address);
        System.out.println("Shipping Method: " + shippingCalculator.getShippingMethod());
        System.out.println("-".repeat(80));
        
        // Print items
        System.out.printf("%-25s %8s %12s %15s%n", "Item", "Qty", "Unit Price", "Total");
        System.out.println("-".repeat(80));
        
        for (CartItem item : cartService.getItems()) {
            System.out.printf("%-25s %8d $%10.2f $%13.2f%n",
                item.getProduct().getName(),
                item.getQuantity(),
                item.getProduct().getPrice(),
                item.getTotalPrice());
        }
        
        System.out.println("-".repeat(80));
        System.out.printf("%-45s $%13.2f%n", "Subtotal:", subtotal);
        System.out.printf("%-45s -$%12.2f%n", "Total Discounts:", totalDiscount);
        System.out.printf("%-45s $%13.2f%n", "Shipping (" + String.format("%.2f", cartService.getTotalWeight()) + " kg):", shippingCost);
        System.out.println("-".repeat(80));
        System.out.printf("%-45s $%13.2f%n", "FINAL TOTAL:", finalTotal);
        System.out.println("=".repeat(80));
    }
}
