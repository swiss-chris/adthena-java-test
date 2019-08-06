## PriceBasket

### Prerequisite
* Java 8 or higher is installed on your system

### Steps to run

1. Clone this repository to your local system.
1. Build the project by running `gradlew build` in the root directory.
1. `cd` into the `build/classes/java/main/` directory.
1. Run the program with `java PriceBasket [items...]`.
1. To see a list of available items, execute `java PriceBasket -help`.
1. The list of available items can be configured in `config.SimpleProductConfigService`, 
   the discount rules in `config.SimpleDiscountConfigService`.
