package products;

// TODO use external config file
public enum Product {

    SOUP(0.65),
    BREAD(0.80),
    MILK(1.30),
    APPLES(1.00);

    private final Double price;

    Product(Double price) {
        this.price = price;
    }

    public static boolean contains(String item) {
        for (Product product : Product.values()) {
            if (product.name().equals(item)) {
                return true;
            }
        }
        return false;
    }

    public Double getPrice() {
        return price;
    }
}
