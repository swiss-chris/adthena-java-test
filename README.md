## PriceBasket

### Prerequisite
* Java 8 (or higher) is installed on your system

### Steps to run

1. Clone this repository to your local system.
1. Build the project by running `./gradlew build` (Linux) or `gradlew build` (Windows) in the root directory.
    * If you're running on Linux, make sure `gradlew` is executable.
1. `cd` into the `build/classes/java/main/` directory.
1. Run the program with `java PriceBasket [items...]`.
    * If the £ sign doesn't display correctly, try `export JAVA_TOOL_OPTIONS="-Dfile.encoding=UTF-8"` or else running the program with `java -Dfile.encoding=UTF-8 PriceBasket [items]`.
1. To see a list of available items, execute `java PriceBasket` or `java PriceBasket -help`.
1. Configuring items and discounts:
    * The list of available items can be configured in `config.SimpleProductConfigService`
    * The discount rules can be configured in `config.SimpleDiscountConfigService`.
