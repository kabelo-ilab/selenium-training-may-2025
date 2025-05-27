package Chapter6;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ParameterizedTests {

    @Test
    void firstTest(){
        //define / preload expected results
        int x = 7;
        int y = 1;
        int expected = 6;

        //Act - actual results
        int actual = x + y;

        //Assert - compare actual results with expected results
        assertEquals(expected,actual, x + " + " + y + " is " + (x + y));

    }

    @ParameterizedTest(name = "Test Even Numbers {index} : {arguments}")
    @ValueSource(ints = {52, 10, 83, 101, 74})
    void testIsEvenNumber(int number){
        assertEquals(0, number % 2, number + " is not an even number");
    }

    @ParameterizedTest(name = "Test for short names {index} : {arguments}")
    @ValueSource(strings = {"David","Kabelo", "Jessica", "Loraine", "Dave"})
    void testShortNames(String name){
        assertTrue(name.length() <= 5, name + " has (" + name.length() + ") characters");
    }

    @ParameterizedTest
    @CsvSource(value = {"Smith,36,1.79,true", "Jessica,29,1.63,true", "Charlize,33,1.77,false", "Roger,39,1.81,true"})
    void testPersonDetails(String name, int age, double height, boolean isMarried){
        System.out.println("Name = " + name + ", Age (" + age + "), Height [" + height + "], Married? " + isMarried);

        assertEquals(true, isMarried, name + " is not married");
    }


    @ParameterizedTest
    @MethodSource("wordsList")
    void testPalindrome(String word){
        StringBuilder sb = new StringBuilder(word);
        sb.reverse();//dad
        assertEquals(word, sb.toString(), word + " is not a palindrome");
    }

    @ParameterizedTest
    @MethodSource
    void wordsList(String word){
        StringBuilder sb = new StringBuilder(word);
        sb.reverse();//dad
        assertEquals(word, sb.toString(), word + " is not a palindrome");
    }

    @ParameterizedTest
    @MethodSource("personalDetails")
    void testPersonalDetails(String name, int age, double height, boolean isMarried){
        System.out.println("Name: " + name + ", Age (" + age + "), Height [" + height + "], Married = " + isMarried);
        assertTrue(age >= 35, name + " is still young, with " + age + " years of age");
    }


    @ParameterizedTest
    @MethodSource("Chapter6.ProductData#productList")
    void testProductList(String product){
        System.out.println("product = " + product);
        assertTrue(product.contains("a"), product + " doesn't contain 'a'");
    }


    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/athletes.csv", numLinesToSkip = 1)
    void testAthletes(String name, String sport, String nationality, double height, int age){

        System.out.println("name = " + name + ", sport = " + sport + ", nationality = " + nationality + ", height = " + height + ", age = " + age);

    }

    List<String> wordsList(){
        return new ArrayList<>(
                List.of("dad", "moon", "taco", "sagas", "mom", "wow", "cat", "rotator"));
    }

    Stream<Arguments> personalDetails(){
        return Stream.of(
                Arguments.arguments("Smith", 25, 1.63, true),
                Arguments.arguments("Roger", 35, 1.73, false),
                Arguments.arguments("Rafael", 41, 1.83, true),
                Arguments.arguments("David", 33, 1.81, false)
        );
    }

}


