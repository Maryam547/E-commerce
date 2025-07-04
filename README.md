# 🛒 Fawry E-Commerce Console App (Java)

A simple yet powerful Java console application that simulates an e-commerce system using clean code architecture, SOLID principles, and design patterns.

## 🚀 Project Overview

This application allows users to:
- Browse available products (Electronics, Clothing, Books)
- Add items to a shopping cart
- Select a shipping method (Standard / Express)
- Apply discounts automatically
- Checkout with a detailed receipt

---

## ✅ Features

- 🧱 Clean, modular design following SOLID principles
- 🎯 Multiple discount strategies (percentage, fixed)
- 🚚 Shipping cost calculation (weight-based, strategy pattern)
- 🛍️ Inventory and cart tracking
- 🔧 Easily extensible to add new product or shipping types

---

## 📁 Project Structure

com.fawry.ecommerce/
├── interfaces/ # Core interfaces (Product, Discount, Shipping, etc.)
├── models/ # Product types and CartItem
├── services/ # Business logic: Cart, Inventory, Checkout, etc.
├── utils/ # Utilities like input handling and sample data
└── ECommerceApplication.java # Main class (entry point)


---

## 🛠️ How to Run

### Using IntelliJ IDEA:
1. Open the project folder in IntelliJ
2. Link your installed JDK (e.g., `G:\Java JDK`)
3. Run `ECommerceApplication.java`

### Using Command Line:
```bash
cd path/to/project
javac -d . src/main/java/com/fawry/ecommerce/**/*.java
java com.fawry.ecommerce.ECommerceApplication
