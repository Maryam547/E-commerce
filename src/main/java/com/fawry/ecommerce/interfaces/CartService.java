package com.fawry.ecommerce.interfaces;

import com.fawry.ecommerce.models.CartItem;
import java.util.List;

public interface CartService {
    void addItem(String productName, int quantity);
    void removeItem(String productName);
    void updateQuantity(String productName, int quantity);
    List<CartItem> getItems();
    double getTotalPrice();
    double getTotalWeight();
    void clearCart();
    boolean isEmpty();
    void displayCart();
}
