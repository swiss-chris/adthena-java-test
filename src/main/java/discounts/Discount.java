package discounts;

import products.Product;

import java.util.ArrayList;
import java.util.List;

public abstract class Discount {

    protected List<Product> products = new ArrayList<>();
    protected Double discount = 0.00;

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Double getDiscount() {
        return discount;
    }

    public abstract void applyDiscount();

    public abstract String getDiscountText();

    public abstract boolean hasDiscount();
}
