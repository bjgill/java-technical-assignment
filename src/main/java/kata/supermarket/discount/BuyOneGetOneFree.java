package kata.supermarket.discount;

import java.math.BigDecimal;
import java.util.Optional;

import kata.supermarket.Item;

public class BuyOneGetOneFree implements Discount {
    private int itemsSeen = 0;

    public Optional<BigDecimal> apply(final Item item) {
        itemsSeen += 1;

        if (itemsSeen % 2 == 0) {
            return Optional.of(item.price());
        }

        return Optional.empty();
    }
}
