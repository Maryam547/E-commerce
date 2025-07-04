package com.fawry.ecommerce.services;


public class StandardShipping implements ShippingCalculator {
    private static final double RATE_PER_KG = 2.0;
    private static final double BASE_COST = 5.0;
    
    @Override
    public double calculateShipping(double weight, String address) {
        double cost = BASE_COST + (weight * RATE_PER_KG);
        
        // Add extra cost for international shipping
        if (address.toLowerCase().contains("international")) {
            cost *= 2.5;
        }
        
        return cost;
    }
    
    @Override
    public String getShippingMethod() {
        return "Standard Shipping";
    }
}
