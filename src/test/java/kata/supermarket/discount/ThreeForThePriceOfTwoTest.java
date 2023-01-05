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

        assertEquals(Optional.empty(), discount.apply(item));
    }

    @Test
    void threeItemsChargedAsTwo() {
        final var discount = new ThreeForThePriceOfTwo();
        final var items = Stream.of(aPackOfDigestives(), aPackOfDigestives(), aPackOfDigestives());

        final var totalDiscount= items.map(item -> discount.apply(item).orElse(BigDecimal.ZERO))
            .reduce(BigDecimal::add)
            .orElse(BigDecimal.ZERO)
            .setScale(2, RoundingMode.HALF_UP);

        assertEquals(aPackOfDigestives().price(), totalDiscount);
    }

    @Test
    void discountCheapestItem() {
        final var discount = new ThreeForThePriceOfTwo();
        final var items = Stream.of(aPackOfDigestives(), aPintOfMilk(), aLoafOfBread());

        final var totalDiscount= items.map(item -> discount.apply(item).orElse(BigDecimal.ZERO))
            .reduce(BigDecimal::add)
            .orElse(BigDecimal.ZERO)
            .setScale(2, RoundingMode.HALF_UP);

        assertEquals(aPintOfMilk().price(), totalDiscount);
    }

    @Test
    void considerEachTripleSeparately() {
        final var discount = new ThreeForThePriceOfTwo();
        final var items = Stream.of(
            aPackOfDigestives(), aPackOfDigestives(), aPackOfDigestives(),
            aPintOfMilk(), aPintOfMilk(), aPintOfMilk()
        );

        final var totalDiscount= items.map(item -> discount.apply(item).orElse(BigDecimal.ZERO))
            .reduce(BigDecimal::add)
            .orElse(BigDecimal.ZERO)
            .setScale(2, RoundingMode.HALF_UP);

        assertEquals(aPackOfDigestives().price().add(aPintOfMilk().price()), totalDiscount);
    }

    private Item aPackOfDigestives() {
        return new Product(new BigDecimal("1.55")).oneOf();
    }

    private Item aPintOfMilk() {
        return new Product(new BigDecimal("0.49")).oneOf();
    }

    private Item aLoafOfBread() {
        return new Product(new BigDecimal("1.49")).oneOf();
    }
}
