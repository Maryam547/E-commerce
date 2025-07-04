package com.fawry.ecommerce.services;

import com.fawry.ecommerce.interfaces.ShippingCalculator;


public class ExpressShipping implements ShippingCalculator {
    private static final double RATE_PER_KG = 4.0;
    private static final double BASE_COST = 15.0;
    
    @Override
    public double calculateShipping(double weight, String address) {
        double cost = BASE_COST + (weight * RATE_PER_KG);
        
        // Add extra cost for international express shipping
        if (address.toLowerCase().contains("international")) {
            cost *= 3.0;
        }
        
        return cost;
    }
    
    @Override
    public String getShippingMethod() {
        return "Express Shipping";
    }
}
