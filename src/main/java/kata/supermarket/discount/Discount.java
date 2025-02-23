package kata.supermarket.discount;

import java.math.BigDecimal;
import java.util.Optional;

import kata.supermarket.Item;

public interface Discount {
    public Optional<BigDecimal> apply(final Item item);
}
