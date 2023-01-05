package kata.supermarket.discount;

import org.junit.jupiter.api.Test;

import kata.supermarket.Item;
import kata.supermarket.ItemByUnit;
import kata.supermarket.Product;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuyOneGetOneFreeTest {
    @Test
    void singleItemHasNoDiscount() {
        final var bogof = new BuyOneGetOneFree();
        final var item = aPackOfDigestives();

        assertEquals(Optional.empty(), bogof.calculateDiscount(item));
    }

    private Item aPackOfDigestives() {
        return new Product(new BigDecimal("1.55")).oneOf();
    }
}
