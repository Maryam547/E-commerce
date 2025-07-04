package com.fawry.ecommerce.models;

import com.fawry.ecommerce.services.Expirable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Books extends BaseProduct implements Expirable {
    private String author;
    private String isbn;
    private LocalDate expiryDate;
    
    public Books(String name, double price, int stock, double weight, String author, String isbn, LocalDate expiryDate) {
        super(name, price, stock, "Books", weight);
        this.author = author;
        this.isbn = isbn;
        this.expiryDate = expiryDate;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    @Override
    public LocalDate getExpiryDate() {
        return expiryDate;
    }
    
    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }
    
    @Override
    public int getDaysUntilExpiry() {
        return (int) ChronoUnit.DAYS.between(LocalDate.now(), expiryDate);
    }
    
    @Override
    public boolean isAvailable() {
        return super.isAvailable() && !isExpired();
    }
    
    @Override
    protected String getSpecificDetails() {
        String expiryInfo = isExpired() ? " (EXPIRED)" : 
                           getDaysUntilExpiry() < 30 ? " (Expires soon)" : "";
        return String.format("Author: %s | ISBN: %s | Expires: %s%s", 
                           author, isbn, expiryDate, expiryInfo);
    }
}
