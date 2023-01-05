package kata.supermarket;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import kata.supermarket.discount.Discount;

public class DiscountSelector {
    private List<Discount> discounts;

    public DiscountSelector(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public Optional<BigDecimal> applyDiscount(Item item) {
        for (Discount discount : discounts) {
            var discountAmount = discount.apply(item);
            if (discountAmount.isPresent()) {
                return discountAmount;
            }
        }
        return Optional.empty();
    }
}
