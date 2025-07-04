package com.fawry.ecommerce.services;

import java.time.LocalDate;

public interface Expirable {
    LocalDate getExpiryDate();
    boolean isExpired();
    int getDaysUntilExpiry();
}
