package org.ECommerceProject.com;

import java.util.HashMap;
import java.util.Scanner;

public class ECommerceApp {
    private HashMap<String, Integer> products = new HashMap<>();
    private double walletBalance = 500.0;

    public ECommerceApp() {
        // Adding products to the catalog
        products.put("Laptop", 700);
        products.put("Smartphone", 300);
        products.put("Headphones", 50);
        products.put("Keyboard", 40);
    }

    public void searchProduct(String productName) throws ProductNotFoundException {
        if (!products.containsKey(productName)) {
            throw new ProductNotFoundException("Product '" + productName + "' not found in the catalog.");
        }
        System.out.println("Product found: " + productName + " - Price: $" + products.get(productName));
    }

    public void addToCart(String productName) throws ProductNotFoundException {
        if (!products.containsKey(productName)) {
            throw new ProductNotFoundException("Cannot add '" + productName + "' to cart. Product not found.");
        }
        System.out.println(productName + " added to cart successfully.");
    }

    public void processPayment(String productName) throws ProductNotFoundException, InsufficientBalanceException {
        if (!products.containsKey(productName)) {
            throw new ProductNotFoundException("Cannot process payment. Product '" + productName + "' not found.");
        }

        int price = products.get(productName);
        if (walletBalance < price) {
            throw new InsufficientBalanceException("Insufficient balance. Your wallet balance is $" + walletBalance +
                    ", but the product costs $" + price);
        }

        walletBalance -= price;
        System.out.println("Payment successful! You purchased: " + productName + ". Remaining balance: $" + walletBalance);
    }

    public static void main(String[] args) {
        ECommerceApp app = new ECommerceApp();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter a product name to search:");
            String productName = scanner.nextLine();
            app.searchProduct(productName);

            System.out.println("Do you want to add this product to the cart? (yes/no)");
            String addToCartResponse = scanner.nextLine();
            if (addToCartResponse.equalsIgnoreCase("yes")) {
                app.addToCart(productName);
            }

            System.out.println("Do you want to purchase this product? (yes/no)");
            String purchaseResponse = scanner.nextLine();
            if (purchaseResponse.equalsIgnoreCase("yes")) {
                app.processPayment(productName);
            }

        } catch (ProductNotFoundException | InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Thank you for visiting our e-commerce platform!");
        }

        scanner.close();
    }
}
