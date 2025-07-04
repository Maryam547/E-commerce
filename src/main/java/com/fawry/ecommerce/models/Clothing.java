package com.fawry.ecommerce.models;


public class Clothing extends BaseProduct {
    private String size;
    private String material;
    
    public Clothing(String name, double price, int stock, double weight, String size, String material) {
        super(name, price, stock, "Clothing", weight);
        this.size = size;
        this.material = material;
    }
    
    public String getSize() {
        return size;
    }
    
    public String getMaterial() {
        return material;
    }
    
    @Override
    protected String getSpecificDetails() {
        return String.format("Size: %s | Material: %s", size, material);
    }
}
