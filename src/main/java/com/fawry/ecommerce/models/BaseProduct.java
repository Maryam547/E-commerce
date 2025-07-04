package com.fawry.ecommerce.models;

import com.fawry.ecommerce.interfaces.Product;


public abstract class BaseProduct implements Product {
    protected String name;
    protected double price;
    protected int stock;
    protected String category;
    protected double weight;
    
    public BaseProduct(String name, double price, int stock, String category, double weight) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.weight = weight;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public double getPrice() {
        return price;
    }
    
    @Override
    public int getStock() {
        return stock;
    }
    
    @Override
    public void setStock(int stock) {
        this.stock = stock;
    }
    
    @Override
    public String getCategory() {
        return category;
    }
    
    @Override
    public double getWeight() {
        return weight;
    }
    
    @Override
    public boolean isAvailable() {
        return stock > 0;
    }
    
    @Override
    public String getProductDetails() {
        return String.format("%-20s | $%-8.2f | Stock: %-3d | Weight: %.2fkg | %s", 
            name, price, stock, weight, getSpecificDetails());
    }
    
    protected abstract String getSpecificDetails();
}
