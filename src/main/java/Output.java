import lombok.Value;

import java.util.List;

@Value
class Output {
    String subtotalText;
    List<String> discountTexts;
    String totalText;
}
