package taskfawry;

public class ShippableProduct extends Product implements Shippable {
    private double weight;

    public ShippableProduct(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    @Override
    public boolean isExpired() {
        return false; // Non-expirable
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
