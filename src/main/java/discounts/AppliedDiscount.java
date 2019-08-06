package discounts;

import java.util.Objects;

// TODO use Lombok
public class AppliedDiscount {

    private final String discountTextPrefix;
    private final Double discountAmount;

    AppliedDiscount(String discountTextPrefix, Double discountAmount) {
        this.discountTextPrefix = discountTextPrefix;
        this.discountAmount = discountAmount;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public Object getDiscountTextPrefix() {
        return discountTextPrefix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppliedDiscount that = (AppliedDiscount) o;
        return discountTextPrefix.equals(that.discountTextPrefix) &&
            discountAmount.equals(that.discountAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discountTextPrefix, discountAmount);
    }

    @Override
    public String toString() {
        return "AppliedDiscount{" +
            "discountTextPrefix='" + discountTextPrefix + '\'' +
            ", discountAmount=" + discountAmount +
            '}';
    }
}
