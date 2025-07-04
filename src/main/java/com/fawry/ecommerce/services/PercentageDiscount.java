package com.fawry.ecommerce.services;

import com.fawry.ecommerce.interfaces.DiscountStrategy;
import com.fawry.ecommerce.models.CartItem;


public class PercentageDiscount implements DiscountStrategy {
    private double percentage;
    private String category;
    
    public PercentageDiscount(double percentage, String category) {
        this.percentage = percentage;
        this.category = category;
    }
    
    @Override
    public double calculateDiscount(CartItem item) {
        if (!isApplicable(item)) {
            return 0;
        }
        return item.getTotalPrice() * (percentage / 100);
    }
    
    @Override
    public String getDiscountDescription() {
        return String.format("%.0f%% off on %s items", percentage, category);
    }
    
    @Override
    public boolean isApplicable(CartItem item) {
        return item.getProduct().getCategory().equalsIgnoreCase(category);
    }
}
