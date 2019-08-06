import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
class Output {
    private String subtotalText = "";
    private List<String> discountTexts = new ArrayList<>();
    private String totalText = "";
}
