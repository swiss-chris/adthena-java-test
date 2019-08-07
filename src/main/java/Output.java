import lombok.Data;

import java.util.List;

@Data
class Output {
    private final String subtotalText;
    private final List<String> discountTexts;
    private final String totalText;
}
