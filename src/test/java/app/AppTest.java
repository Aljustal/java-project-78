package app;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    @Test
    void testTest()  {

        String actual = ("hi");
        String expectedString = ("hi");

        assertThat(actual).isEqualTo(expectedString);
    }
}
