package taskfawry;

import java.time.LocalDate;

public class TaskFawry {

    public static void main(String[] args) {

        
        Customer customer = new Customer("Ahmed", 800); 

        Product cheese = new ExpirableProduct("Cheese 200g", 100, 5, LocalDate.of(2025, 8, 1));
        Product biscuits = new ExpirableProduct("Biscuits 700g", 150, 3, LocalDate.of(2025, 7, 10));
        Product tv = new ShippableProduct("TV", 300, 3, 5.0);
        Product scratchCard = new Product("Scratch Card", 50, 10) {
            @Override
            public boolean isExpired() {
                return false;
            }
        };

        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(tv, 1);
        cart.add(scratchCard, 1);

        // Checkout
        CheckoutService.checkout(customer, cart);
    }
}
