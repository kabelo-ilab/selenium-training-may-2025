package Chapter6;

import java.util.stream.Stream;

public class ProductData {

   static Stream<String> productList(){
        return Stream.of("Apple", "Orange", "Mango", "Banana");
    }

}
