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

## v0.1 discounter

* The discounter is going to need to be stateful - items can't hold the state since discounts cross multiple items.
* Let's assume we can calculate discounts on individual items as they come in. Later, we may want to refactor the basket to group sets of identical items.
* Pre-commit tests, CI and all that jazz would be lovely.
* Yes, I'm now really convinced we've got a problem with rounding.
* We will ultimately need a way to load the mapping of discounts to items (and change said mapping). However, that's not very exciting, so let's just hard code it for now.
* I'd like to commonise some of the test fixtures and create some helper functions. We're just at the point where the copying is getting excessive.
* Does the optional actually help? Here, absolutely not. I have a suspicion that it will be useful for composing, though, so let's keep it in.

## Post-mortem notes
* I like the approach of making discounts stateful. It means that they're all isolated, and we don't need some hideous shared state tracking that handles all the state needed by all possible discounts.
* Also, it should be easy to extend the discounts I've created to cover the cases for arbitrary numbers.
* We want to change the discount to operate on the entire list. This is necessary for some discounts that might involve applying discounts to early members of the basket due to the presence of later items (e.g. "buy one kilo of vegetables for half price").
* The hard problem is composition. We either need to add logic to the discount selector to make it impossible to configure multiple discounts for one item, or solve it properly. This will depend heavily on the business needs.
* On a related note, using a list of discounts in the discount selector was a naive way of 'solving' composition, but probably introduces more problems than it solves. I think the right way instead would be to have discounts be a `Map<ProductId, Discount>` (or maybe a `Map<ProductId, List<Discount>>` fort composition).
* Also, we probably want to change how discounts are handled in the basket. Currently, the price and discount are handled separately. This is likely to complicate tax calculations. Instead of returning the discount, I think we want to return an object containing both the original price and the post-discount price. We could then apply taxes, and do all the summation at the end. This would also have the nice side-effect of making it much easier to check for discounts causing negative prices or price increases.
* Finally, not sure how performant this would be. At a guess, a large supermarket might have 10 000 - 100 000 different products, a substantial fraction of which might have discounts. If this is running on a point of sale terminal, we have very limited computing resources. We probably want to do some early performance testing to check that this approach is reasonable.
