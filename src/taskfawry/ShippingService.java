package taskfawry;

import java.util.List;

public class ShippingService {
    public static void shipItems(List<Shippable> items) {
        if (items.isEmpty()) return;

        System.out.println("** Shipment notice **");

        double totalWeight = 0.0;
        for (Shippable item : items) {
            System.out.println(item.getName());
            totalWeight += item.getWeight();
        }

        System.out.printf("Total package weight %.1fkg\n", totalWeight);
    }
}
