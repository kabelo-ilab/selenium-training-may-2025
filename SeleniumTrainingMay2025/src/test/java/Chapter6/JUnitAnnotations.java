package Chapter6;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JUnitAnnotations {

    int x, y;
    @Test
    @DisplayName("First Test")
    @Tag("Regression")
    @Order(3)
    void firstTest(){
        System.out.println("First test");
        System.out.println(x * y);
    }

    @Test
    @DisplayName("Second Test")
    @Tag("Regression")
    @Tag("Sanity")
    @Order(1)
    void secondTest(){
        System.out.println("Second Test");
        System.out.println(x + y);
    }

    @Test
    @DisplayName("Third Test")
    @Tag("Sanity")
    @Order(2)
    void thirdTest(){
        System.out.println("Third Test");
        System.out.println(x / y);
    }
    @Test
    @DisplayName("Forth Test")
    @Tag("Sanity")
    @Order(4)
    void forthTest(){
        System.out.println("Forth Test");
        System.out.println(x - y);
    }

    @BeforeEach
    @DisplayName("Before Each")
    void beforeAnyTest(){
        System.err.println("Before Each Method");
    }

    @AfterEach
    @DisplayName("Before Each")
    void afterAnyTest(){
        x = x + 3;
        y = y + 7;
        System.err.println("After Each Method");
    }

    @BeforeAll
    void beforeAllTests(){
        x = 5;
        y = 10;
        System.out.println("Before All");
    }
    @AfterAll
    void afterAllTests(){
        System.out.println("After All Tests");
    }
}
