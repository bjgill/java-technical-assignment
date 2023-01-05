# Notes

## Initial thoughts

* Rounding?
* If e.g. 2 for 1 across a group, how do we choose which to apply it to?
* Need to ensure that it's impossible to have too many discounts to make the price negative - with more time, might be nice to add property testing?
* Relatedly, how do discounts compose? We might want some discounts to stack (e.g. employee discounts), but most are likely to be mutually exclusive (and then we will need to decide which of the possible discounts to apply).
* I think there are two separate things here:
    1. a discounter (?) that applies the relevant discounts
    2. a way to select the discounts that apply to an item. We probably need a map of item to discount, but humans probably want to configure discounts the other way round: there are likely to be far more types of items than types of discounts.
* Also, need to ensure that discounts don't increase the price (e.g. the 2 for £1 discount) - again, something a bit of property testing might be good for.
* I think the place to start is with the discounter. I will assume a spherical supermarket that only sells unit items and wants discounts for them. I'll implement a couple of diffrent discounts, and then look into the selector.
