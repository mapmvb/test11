package com.luxoft.unit;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppTest {
    private static int result;

    @BeforeAll
    static void setUp() {
        result = 5;
        System.out.println("BeforeAll methods");
    }

    @BeforeEach
    void  be() {
        System.out.println("before each method");
    }

    @AfterEach
    void ae() {
        System.out.println("after each method");
    }

    @Order(1)
    @RepeatedTest(4)
    public void firstTest() {
        System.out.println("test method 1");
        Assertions.assertEquals(result, 3+2, "message in test1");
    }

    @Order(2)
    @Test
    public void secondTest() {
        System.out.println("test method 2");
        Assertions.assertEquals(result, 1+4, "message in test2");
    }

    @AfterAll
    static void teardown() {
        System.out.println("After all methods");
    }

}
