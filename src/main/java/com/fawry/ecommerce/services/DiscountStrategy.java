package com.fawry.ecommerce.services;

import com.fawry.ecommerce.models.CartItem;


public interface DiscountStrategy {
    double calculateDiscount(CartItem item);
    String getDiscountDescription();
    boolean isApplicable(CartItem item);
}
