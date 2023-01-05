package kata.supermarket;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;

class DiscounterTest {
    @Test
    void shouldApplyDiscounts(){
        var discounter = new Discounter();
        discounter.registerDiscounts();

        assertEquals(Optional.empty(), discounter.applyDiscount(aPackOfDigestives()));
        assertEquals(Optional.of(aPintOfMilk().price()), discounter.applyDiscount(aPintOfMilk()));
    }
    
    private Item aPackOfDigestives() {
        return new Product(new BigDecimal("1.55")).oneOf();
    }

    private Item aPintOfMilk() {
        return new Product(new BigDecimal("0.49")).oneOf();
    }
}
