package com.fawry.ecommerce.models;

public class Electronics extends BaseProduct {
    private String brand;
    private int warrantyPeriod; // in months
    
    public Electronics(String name, double price, int stock, double weight, String brand, int warrantyPeriod) {
        super(name, price, stock, "Electronics", weight);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }
    
    public String getBrand() {
        return brand;
    }
    
    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }
    
    @Override
    protected String getSpecificDetails() {
        return String.format("Brand: %s | Warranty: %d months", brand, warrantyPeriod);
    }
}
