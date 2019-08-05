package discounts;

public class DiscountTest {

    private Discount discount = createDiscountMock();

    // empty implementation
    private Discount createDiscountMock() {
        return new Discount() {
            @Override
            public void applyDiscount() {
            }

            @Override
            public String getDiscountText() {
                return null;
            }

            @Override
            public boolean hasDiscount() {
                return false;
            }
        };
    }
}