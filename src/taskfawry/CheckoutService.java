package taskfawry;

import java.util.ArrayList;
import java.util.List;

public class CheckoutService {

    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            System.out.println("Error: Cart is empty.");
            return;
        }

        List<Shippable> shippableItems = new ArrayList<>();
        double subtotal = 0.0;
        double totalWeight = 0.0;

        try {
            for (CartItem item : cart.getItems()) {
                Product product = item.getProduct();

                // Check expiry again
                if (product.isExpired()) {
                    throw new IllegalArgumentException("Product " + product.getName() + " is expired.");
                }

                product.reduceQuantity(item.getQuantity());

                double itemTotal = item.getTotalPrice();
                subtotal += itemTotal;

                // If shippable, add to shipping list with repeated quantity
                if (product instanceof Shippable) {
                    for (int i = 0; i < item.getQuantity(); i++) {
                        shippableItems.add((Shippable) product);
                        totalWeight += ((Shippable) product).getWeight();
                    }
                }
            }

            double shippingCost = calculateShipping(totalWeight);
            double total = subtotal + shippingCost;

            if (customer.getBalance() < total) {
                throw new IllegalArgumentException("Customer has insufficient balance.");
            }

            customer.deduct(total);

            // Print shipment info
            ShippingService.shipItems(shippableItems);

            // Print checkout receipt
            System.out.println("\n** Checkout receipt **");
            for (CartItem item : cart.getItems()) {
                System.out.printf("%dx %s %.0f\n",
                        item.getQuantity(),
                        item.getProduct().getName(),
                        item.getTotalPrice());
            }

            System.out.println("----------------------");
            System.out.printf("Subtotal %.0f\n", subtotal);
            System.out.printf("Shipping %.0f\n", shippingCost);
            System.out.printf("Amount %.0f\n", total);
            System.out.printf("Customer Balance: %.0f\n", customer.getBalance());
            System.out.println("END");

        } catch (Exception e) {
            System.out.println("Checkout failed: " + e.getMessage());
        }
    }

    private static double calculateShipping(double weight) {
        return weight * 10; // Shipping = 10 per kg
    }
}
