package kata.supermarket;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import kata.supermarket.discount.BuyOneGetOneFree;
import kata.supermarket.discount.Discount;

public class Discounter {
    private List<Discount> discounts = List.of();

    public void registerDiscounts() {
        discounts = List.of(new BuyOneGetOneFree());
    }

    public Optional<BigDecimal> applyDiscount(Item item) {
        for (Discount discount : discounts) {
            var discountAmount = discount.calculateDiscount(item);
            if (discountAmount.isPresent()) {
                return discountAmount;
            }
        }
        return Optional.empty();
    }
}
