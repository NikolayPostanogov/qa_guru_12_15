package guru.qa.simple;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SkippedTest {
    @Test
    @Disabled
    void test00() {
        assertTrue(false);
    }
    @Test
    @Disabled
    void test01() {
        assertTrue( false);
    }
    @Test
    @Disabled("some reason")
    void test02() {
        assertTrue(false);
    }
}
