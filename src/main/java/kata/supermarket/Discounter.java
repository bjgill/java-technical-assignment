package kata.supermarket;

import java.math.BigDecimal;
import java.util.Optional;

import kata.supermarket.discount.BuyOneGetOneFree;
import kata.supermarket.discount.Discount;

public class Discounter {

    public void registerDiscounts() {
    }

    public Optional<BigDecimal> applyDiscount(Item item) {
        return Optional.empty();
    }
}
