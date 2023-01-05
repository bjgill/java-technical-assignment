package kata.supermarket;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import kata.supermarket.discount.BuyOneGetOneFree;
import kata.supermarket.discount.Discount;

class DiscountSelectorTest {
    @Test
    void shouldApplyDiscount() {
        var discounter = new DiscountSelector(List.of(new ConstantDiscount()));

        assertEquals(Optional.of(BigDecimal.ONE), discounter.applyDiscount(aPintOfMilk()));
    }

    @Test
    void shouldApplyFirstDiscountOnly() {
        var discounter = new DiscountSelector(List.of(
            new ConstantDiscount(), new BuyOneGetOneFree()
        ));

        assertEquals(Optional.of(BigDecimal.ONE), discounter.applyDiscount(aPintOfMilk()));
        assertEquals(Optional.of(BigDecimal.ONE), discounter.applyDiscount(aPintOfMilk()));
    }

    private Item aPintOfMilk() {
        return new Product(new BigDecimal("0.49")).oneOf();
    }

    private class ConstantDiscount implements Discount {
        @Override
        public Optional<BigDecimal> apply(Item item) {
            return Optional.of(BigDecimal.ONE);
        }

    }
}
