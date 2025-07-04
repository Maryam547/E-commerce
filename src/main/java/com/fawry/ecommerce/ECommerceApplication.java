package com.fawry.ecommerce;

import com.fawry.ecommerce.services.CartService;
import com.fawry.ecommerce.services.ShippingCalculator;
import com.fawry.ecommerce.services.*;
import com.fawry.ecommerce.utils.DataInitializer;
import com.fawry.ecommerce.utils.InputUtils;


public class ECommerceApplication {
    private InventoryService inventoryService;
    private CartService cartService;
    private CheckoutService checkoutService;
    
    public ECommerceApplication() {
        initializeServices();
    }
    
    private void initializeServices() {
        // Initialize services following Dependency Injection
        inventoryService = new InventoryService();
        cartService = new ShoppingCartService(inventoryService);
        checkoutService = new CheckoutService(cartService, inventoryService);
        
        // Initialize sample data
        DataInitializer.initializeInventory(inventoryService);
        
        // Setup discount strategies
        checkoutService.addDiscountStrategy(new PercentageDiscount(10, "Electronics"));
        checkoutService.addDiscountStrategy(new PercentageDiscount(15, "Clothing"));
        checkoutService.addDiscountStrategy(new FixedDiscount(5, 50));
        
        System.out.println("Fawry E-commerce System initialized successfully!");
    }
    
    public void run() {
        displayWelcome();
        
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = InputUtils.getChoice("Enter your choice: ", 1, 7);
            
            switch (choice) {
                case 1:
                    viewInventory();
                    break;
                case 2:
                    addToCart();
                    break;
                case 3:
                    viewCart();
                    break;
                case 4:
                    manageCart();
                    break;
                case 5:
                    selectShippingAndCheckout();
                    break;
                case 6:
                    viewCartSummary();
                    break;
                case 7:
                    running = false;
                    System.out.println("Thank you for using Fawry E-commerce System!");
                    break;
            }
            
            if (running) {
                InputUtils.waitForEnter();
            }
        }
    }
    
    private void displayWelcome() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("                    WELCOME TO FAWRY E-COMMERCE SYSTEM");
        System.out.println("                         Full Stack Development Challenge");
        System.out.println("=".repeat(80));
    }
    
    private void displayMainMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                  MAIN MENU");
        System.out.println("=".repeat(50));
        System.out.println("1. View Inventory");
        System.out.println("2. Add Item to Cart");
        System.out.println("3. View Cart");
        System.out.println("4. Manage Cart (Update/Remove)");
        System.out.println("5. Select Shipping & Checkout");
        System.out.println("6. Cart Summary");
        System.out.println("7. Exit");
        System.out.println("=".repeat(50));
    }
    
    private void viewInventory() {
        System.out.println("\n--- INVENTORY VIEW ---");
        inventoryService.displayInventory();
    }
    
    private void addToCart() {
        System.out.println("\n--- ADD TO CART ---");
        inventoryService.displayInventory();
        
        String productName = InputUtils.getString("\nEnter product name: ");
        int quantity = InputUtils.getInt("Enter quantity: ");
        
        cartService.addItem(productName, quantity);
    }
    
    private void viewCart() {
        System.out.println("\n--- SHOPPING CART ---");
        cartService.displayCart();
    }
    
    private void manageCart() {
        if (cartService.isEmpty()) {
            System.out.println("\nYour cart is empty!");
            return;
        }
        
        System.out.println("\n--- MANAGE CART ---");
        cartService.displayCart();
        
        System.out.println("\n1. Update quantity");
        System.out.println("2. Remove item");
        System.out.println("3. Clear cart");
        System.out.println("4. Back to main menu");
        
        int choice = InputUtils.getChoice("Choose action: ", 1, 4);
        
        switch (choice) {
            case 1:
                String productName = InputUtils.getString("Enter product name to update: ");
                int newQuantity = InputUtils.getInt("Enter new quantity (0 to remove): ");
                cartService.updateQuantity(productName, newQuantity);
                break;
            case 2:
                String productToRemove = InputUtils.getString("Enter product name to remove: ");
                cartService.removeItem(productToRemove);
                break;
            case 3:
                if (InputUtils.getConfirmation("Are you sure you want to clear the cart?")) {
                    cartService.clearCart();
                }
                break;
            case 4:
                return;
        }
    }
    
    private void selectShippingAndCheckout() {
        if (cartService.isEmpty()) {
            System.out.println("\nCannot checkout: Cart is empty!");
            return;
        }
        
        System.out.println("\n--- SHIPPING & CHECKOUT ---");
        cartService.displayCart();
        
        // Select shipping method
        System.out.println("\nSelect Shipping Method:");
        System.out.println("1. Standard Shipping ($2/kg + $5 base)");
        System.out.println("2. Express Shipping ($4/kg + $15 base)");
        
        int shippingChoice = InputUtils.getChoice("Choose shipping method: ", 1, 2);
        
        ShippingCalculator shipping;
        if (shippingChoice == 1) {
            shipping = new StandardShipping();
        } else {
            shipping = new ExpressShipping();
        }
        
        checkoutService.setShippingCalculator(shipping);
        
        // Get customer details
        String customerName = InputUtils.getString("Enter your name: ");
        String address = InputUtils.getString("Enter your address: ");
        
        // Show estimated costs
        double shippingCost = shipping.calculateShipping(cartService.getTotalWeight(), address);
        System.out.println("\nShipping Method: " + shipping.getShippingMethod());
        System.out.printf("Estimated Shipping Cost: $%.2f%n", shippingCost);
        
        if (InputUtils.getConfirmation("Proceed with checkout?")) {
            checkoutService.processCheckout(customerName, address);
        } else {
            System.out.println("Checkout cancelled.");
        }
    }
    
    private void viewCartSummary() {
        System.out.println("\n--- CART SUMMARY ---");
        if (cartService.isEmpty()) {
            System.out.println("Your cart is empty!");
            return;
        }
        
        cartService.displayCart();
        
        // Show potential discounts
        System.out.println("\nAvailable Discounts:");
        System.out.println("• 10% off Electronics items");
        System.out.println("• 15% off Clothing items");
        System.out.println("• $5 off orders over $50");
        
        // Show shipping options
        System.out.println("\nShipping Options:");
        System.out.printf("• Standard: $%.2f%n", 
            new StandardShipping().calculateShipping(cartService.getTotalWeight(), "local"));
        System.out.printf("• Express: $%.2f%n", 
            new ExpressShipping().calculateShipping(cartService.getTotalWeight(), "local"));
    }
    
    public static void main(String[] args) {
        try {
            ECommerceApplication app = new ECommerceApplication();
            app.run();
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
