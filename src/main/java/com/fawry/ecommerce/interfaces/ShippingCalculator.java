package com.fawry.ecommerce.interfaces;


public interface ShippingCalculator {
    double calculateShipping(double weight, String address);
    String getShippingMethod();
}
