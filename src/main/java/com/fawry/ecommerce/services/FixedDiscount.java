package com.fawry.ecommerce.services;

import com.fawry.ecommerce.models.CartItem;


public class FixedDiscount implements DiscountStrategy {
    private double amount;
    private double minimumPurchase;
    
    public FixedDiscount(double amount, double minimumPurchase) {
        this.amount = amount;
        this.minimumPurchase = minimumPurchase;
    }
    
    @Override
    public double calculateDiscount(CartItem item) {
        if (!isApplicable(item)) {
            return 0;
        }
        return Math.min(amount, item.getTotalPrice());
    }
    
    @Override
    public String getDiscountDescription() {
        return String.format("$%.2f off on purchases over $%.2f", amount, minimumPurchase);
    }
    
    @Override
    public boolean isApplicable(CartItem item) {
        return item.getTotalPrice() >= minimumPurchase;
    }
}
