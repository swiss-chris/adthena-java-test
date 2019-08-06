import java.util.ArrayList;
import java.util.List;

class Output {

    private String subtotalText = "";
    private List<String> discountTexts = new ArrayList<>();
    private String totalText = "";

    String getSubtotalText() {
        return subtotalText;
    }

    void setSubtotalText(String subtotalText) {
        this.subtotalText = subtotalText;
    }

    List<String> getDiscountTexts() {
        return discountTexts;
    }

    String getTotalText() {
        return totalText;
    }

    void setTotalText(String totalText) {
        this.totalText = totalText;
    }
}
