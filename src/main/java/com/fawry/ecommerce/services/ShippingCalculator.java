package com.fawry.ecommerce.services;


public interface ShippingCalculator {
    double calculateShipping(double weight, String address);
    String getShippingMethod();
}
