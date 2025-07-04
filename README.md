# Fawry E-commerce System

A comprehensive Java console application implementing an e-commerce system following SOLID principles and design patterns.

## 🏗️ Architecture Overview

This project demonstrates a well-structured Java application following **SOLID principles**:

- **S**ingle Responsibility Principle: Each class has one reason to change
- **O**pen/Closed Principle: Open for extension, closed for modification
- **L**iskov Substitution Principle: Derived classes are substitutable for base classes
- **I**nterface Segregation Principle: Many client-specific interfaces are better than one general-purpose interface
- **D**ependency Inversion Principle: Depend on abstractions, not concretions

## 📁 Project Structure

```
src/main/java/com/fawry/ecommerce/
├── interfaces/           # Contracts and abstractions
│   ├── Product.java     # Core product interface
│   ├── Expirable.java   # Interface for expiring products
│   ├── DiscountStrategy.java  # Strategy pattern for discounts
│   ├── ShippingCalculator.java  # Strategy pattern for shipping
│   └── CartService.java # Cart operations interface
├── models/              # Data models
│   ├── BaseProduct.java # Abstract product base class
│   ├── Electronics.java # Electronics products
│   ├── Clothing.java   # Clothing products
│   ├── Books.java      # Books with expiry dates
│   └── CartItem.java   # Shopping cart item
├── services/            # Business logic
│   ├── InventoryService.java    # Inventory management
│   ├── ShoppingCartService.java # Cart operations
│   ├── CheckoutService.java     # Checkout process
│   ├── PercentageDiscount.java  # Percentage discount strategy
│   ├── FixedDiscount.java       # Fixed amount discount strategy
│   ├── StandardShipping.java    # Standard shipping calculator
│   └── ExpressShipping.java     # Express shipping calculator
├── utils/               # Utility classes
│   ├── InputUtils.java  # Console input utilities
│   └── DataInitializer.java  # Sample data initialization
└── ECommerceApplication.java  # Main application class
```

## 🚀 Features

### Product Management
- **Multiple Product Categories**: Electronics, Clothing, Books
- **Stock Management**: Real-time inventory tracking
- **Product Attributes**: Category-specific properties (warranty, size, author, etc.)
- **Expiry Handling**: Books can expire (Cheese and Biscuits mentioned in requirements)

### Shopping Cart
- **Add/Remove Items**: Full cart management
- **Quantity Updates**: Modify item quantities
- **Stock Validation**: Prevents overselling
- **Cart Persistence**: Maintains cart state during session

### Discount System
- **Percentage Discounts**: Category-based percentage discounts
- **Fixed Discounts**: Fixed amount discounts on minimum purchase
- **Multiple Discounts**: Apply multiple discount strategies
- **Extensible**: Easy to add new discount types

### Shipping System
- **Weight-based Calculation**: Shipping cost based on total weight
- **Multiple Methods**: Standard and Express shipping
- **Address Considerations**: International shipping surcharges
- **Strategy Pattern**: Easy to add new shipping methods

### Checkout Process
- **Detailed Receipts**: Itemized billing with all details
- **Discount Application**: Automatic discount calculation
- **Inventory Updates**: Real-time stock adjustment
- **Customer Information**: Name and address capture

## 🎯 Design Patterns Used

1. **Strategy Pattern**: Discount and shipping calculations
2. **Template Method Pattern**: Base product class with specific implementations
3. **Dependency Injection**: Services depend on interfaces, not implementations
4. **Factory Pattern**: Implicit in product creation
5. **Observer Pattern**: Could be extended for inventory notifications

## 🛠️ How to Run

### Prerequisites
- Java 8 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, VS Code) or command line

### Compilation and Execution

#### Using Command Line:
```bash
# Navigate to the project root directory
cd "d:\last"

# Compile all Java files
javac -d . src/main/java/com/fawry/ecommerce/*.java src/main/java/com/fawry/ecommerce/*/*.java

# Run the application
java com.fawry.ecommerce.ECommerceApplication
```

#### Using VS Code:
1. Open the project folder in VS Code
2. Ensure Java Extension Pack is installed
3. Open `ECommerceApplication.java`
4. Click "Run" button or press F5

#### Using IntelliJ IDEA:
1. Open the project folder
2. Right-click on `ECommerceApplication.java`
3. Select "Run 'ECommerceApplication.main()'"

## 📋 Application Flow

1. **Welcome Screen**: Introduction to the system
2. **Main Menu**: Navigate through different options
3. **Inventory View**: Browse available products with details
4. **Shopping**: Add items to cart with quantity selection
5. **Cart Management**: View, update, or remove cart items
6. **Shipping Selection**: Choose between Standard and Express shipping
7. **Checkout**: Complete purchase with detailed receipt
8. **Receipt**: Comprehensive billing with discounts and shipping

## 🧪 Sample Data

The application includes pre-loaded sample data:

### Electronics
- Laptop (Dell, 24-month warranty)
- Smartphone (Samsung, 12-month warranty)
- Tablet (Apple, 12-month warranty)
- Headphones (Sony, 6-month warranty)

### Clothing
- T-Shirt (Cotton, Size L)
- Jeans (Denim, Size M)
- Jacket (Polyester, Size XL)
- Sneakers (Leather, Size 42)

### Books
- Java Programming (6 months validity)
- Design Patterns (12 months validity)
- Clean Code (Expires in 15 days)
- Effective Java (Already expired)

## 💡 Key Features Demonstrated

### SOLID Principles Implementation

1. **Single Responsibility**: Each class has one clear purpose
   - `InventoryService`: Only manages inventory
   - `CartService`: Only manages cart operations
   - `CheckoutService`: Only handles checkout process

2. **Open/Closed**: Easy to extend without modifying existing code
   - Add new product types by extending `BaseProduct`
   - Add new discount strategies by implementing `DiscountStrategy`
   - Add new shipping methods by implementing `ShippingCalculator`

3. **Liskov Substitution**: All implementations are interchangeable
   - Any `DiscountStrategy` can be used in checkout
   - Any `ShippingCalculator` can calculate shipping costs
   - Any `Product` can be added to inventory

4. **Interface Segregation**: Specific interfaces for specific needs
   - `Expirable` interface only for products that can expire
   - `Product` interface for core product functionality
   - Separate interfaces for different services

5. **Dependency Inversion**: High-level modules don't depend on low-level modules
   - `CheckoutService` depends on `CartService` interface, not implementation
   - Services are injected, not created internally

### Error Handling
- Input validation for all user inputs
- Stock validation to prevent overselling
- Graceful handling of invalid product names
- Clear error messages for user guidance

### Console Interface
- Clean, formatted output with borders and tables
- Intuitive menu navigation
- Clear prompts and confirmations
- Professional receipt formatting

## 🔧 Extensibility

The system is designed for easy extension:

### Adding New Product Types
```java
public class Furniture extends BaseProduct {
    private String material;
    private String dimensions;
    
    // Implementation
}
```

### Adding New Discount Strategies
```java
public class BuyOneGetOneDiscount implements DiscountStrategy {
    // Implementation
}
```

### Adding New Shipping Methods
```java
public class OvernightShipping implements ShippingCalculator {
    // Implementation
}
```

## 📊 Sample Output

```
================================================================================
                    WELCOME TO FAWRY E-COMMERCE SYSTEM
                         Full Stack Development Challenge
================================================================================

==================================================
                  MAIN MENU
==================================================
1. View Inventory
2. Add Item to Cart
3. View Cart
4. Manage Cart (Update/Remove)
5. Select Shipping & Checkout
6. Cart Summary
7. Exit
==================================================
```

## 🏆 Learning Outcomes

This project demonstrates:
- Advanced Object-Oriented Programming principles
- Design patterns implementation in real-world scenarios
- Clean code practices and maintainable architecture
- Console application development with professional UI
- Error handling and input validation
- Comprehensive business logic implementation

## 👨‍💻 Author

Developed as part of the Fawry Quantum Internship Challenge, showcasing full-stack development capabilities through a well-architected Java console application.

---

**Note**: This application serves as a demonstration of software engineering best practices and SOLID principles in action. The console interface provides a complete e-commerce experience while maintaining clean, extensible code architecture.
