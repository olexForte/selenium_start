package mockTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

public class PerMethodLifecycleTest {
    public PerMethodLifecycleTest() {
        System.out.println("Constructor");
    }

    @BeforeAll
    public static void beforeTheEntireTestFixture() {
        System.out.println("Before the entire test fixture");
    }

    @AfterAll
    public static void afterTheEntireTestFixture() {
        System.out.println("After the entire test fixture");
    }

    @BeforeEach
    public void beforeEachTest() {
        System.out.println("Before each test");
    }

    @AfterEach
    public void afterEachTest() {
        System.out.println("After each test");
    }

    @Disabled("testing disabled feature")
    @Test
    public void firstTest() {
        System.out.println("First test");
    }

    @Test
    public void secondTest() {
        System.out.println("Second test");
    }
}