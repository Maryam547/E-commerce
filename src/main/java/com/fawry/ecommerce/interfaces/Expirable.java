package com.fawry.ecommerce.interfaces;

import java.time.LocalDate;

public interface Expirable {
    LocalDate getExpiryDate();
    boolean isExpired();
    int getDaysUntilExpiry();
}
