package kata.supermarket.discount;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import kata.supermarket.Item;
import kata.supermarket.Product;

class ThreeForThePriceOfTwoTest {
    @Test
    void singleItemHasNoDiscount() {
        final var discount = new ThreeForThePriceOfTwo();
        final var item = aPackOfDigestives();

        assertEquals(Optional.empty(), discount.calculateDiscount(item));
    }

    @Test
    void threeItemsChargedAsTwo() {
        final var discount = new ThreeForThePriceOfTwo();
        final var items = Stream.of(aPackOfDigestives(), aPackOfDigestives(), aPackOfDigestives());

        final var totalDiscount= items.map(item -> discount.calculateDiscount(item).orElse(BigDecimal.ZERO))
            .reduce(BigDecimal::add)
            .orElse(BigDecimal.ZERO)
            .setScale(2, RoundingMode.HALF_UP);

        assertEquals(new BigDecimal("1.55"), totalDiscount);
    }

    private Item aPackOfDigestives() {
        return new Product(new BigDecimal("1.55")).oneOf();
    }
}
