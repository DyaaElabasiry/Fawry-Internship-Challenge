package fawry;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles shipping logistics, including calculating fees and generating notices.
 * It operates on a list of Shippable items, decoupled from the main product types.
 */
public class ShippingService {

    
    private static final double BASE_FEE = 10.0;
    private static final double FEE_PER_KG = 20.0;

    
    public void printShipmentNotice(List<Shippable> itemsToShip) {
        if (itemsToShip.isEmpty()) {
            return;
        }
        System.out.println("\n** Shipment notice **");

        // Group items by name to aggregate them for printing.
        itemsToShip.stream()
            .collect(Collectors.groupingBy(Shippable::getName))
            .forEach((name, items) -> {
                long quantity = items.size();
                // Assumption: all items with the same name are identical and have the same weight.
                double unitWeight = items.get(0).getWeight();
                System.out.printf("%dx %s  %.0fg\n", quantity, name, (unitWeight * quantity * 1000));
            });

        double totalWeight = itemsToShip.stream().mapToDouble(Shippable::getWeight).sum();
        System.out.printf("Total package weight %.1fkg\n", totalWeight);
    }

    
    public double calculateShippingFee(List<Shippable> itemsToShip) {
        double totalWeight = itemsToShip.stream()
                .mapToDouble(Shippable::getWeight)
                .sum();

        if (totalWeight == 0) {
            return 0.0;
        }

        
        return BASE_FEE + (Math.floor(totalWeight) * FEE_PER_KG);
    }
}
