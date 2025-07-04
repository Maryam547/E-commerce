package com.fawry.ecommerce.interfaces;



public interface Product {
    String getName();
    double getPrice();
    int getStock();
    void setStock(int stock);
    String getCategory();
    double getWeight();
    boolean isAvailable();
    String getProductDetails();
}
