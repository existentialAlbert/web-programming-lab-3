package test;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class PointTest {

    @BeforeEach
    void setUp() {
        System.out.println(1);

    }

    @AfterEach
    void tearDown() {
        System.out.println(2);

    }

    @Test
    void check() {
        System.out.println(3);
    }

    @Test
    void check1() {
        System.out.println(4);

    }

    @Test
    void check2() {
        System.out.println(5);

    }
}