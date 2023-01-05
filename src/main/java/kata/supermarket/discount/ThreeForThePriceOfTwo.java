package kata.supermarket.discount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import kata.supermarket.Item;

public class ThreeForThePriceOfTwo implements Discount {
    private ArrayList<Item> items = new ArrayList<Item>();

    @Override
    public Optional<BigDecimal> calculateDiscount(Item item) {
        items.add(item);

        if (items.size() == 3) {
            var discount = items.get(0).price().min(items.get(1).price()).min(items.get(2).price());
            items.clear();
            return Optional.of(discount);
        }

        return Optional.empty();
    }
}
