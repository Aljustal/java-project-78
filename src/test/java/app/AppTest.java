package app;

import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    private final Validator validator = new Validator();
    private final StringSchema stringSchema = validator.string();
    @Test
    void testStringSchema1()  {
        Boolean actual = stringSchema.isValid("");
        assertThat(actual).isEqualTo(true);
    }
    @Test
    void testStringSchema2() {
        stringSchema.required();
        Boolean actual = stringSchema.isValid("what does the fox say");
        assertThat(actual).isEqualTo(true);
    }
    @Test
    void testStringSchema3() {
        stringSchema.required();
        Boolean actual = stringSchema.isValid(null);
        assertThat(actual).isEqualTo(false);
    }
    @Test
    void testStringSchema4() {
        stringSchema.required();
        Boolean actual = stringSchema.isValid("");
        assertThat(actual).isEqualTo(false);
    }

    @Test
    void testStringSchema5() {
        Boolean actual1 = stringSchema.contains("wh")
                .isValid("what does the fox say");
        assertThat(actual1).isEqualTo(true);

        Boolean actual2 = stringSchema.contains("whatthe")
                .isValid("what does the fox say");
        assertThat(actual2).isEqualTo(false);

        Boolean actual3 = stringSchema.isValid("what does the fox say");
        assertThat(actual3).isEqualTo(false);
    }
    @Test
    void testStringSchema6() {
        stringSchema.minLength(Integer.parseInt("5"));
        Boolean actual = stringSchema.isValid("what does the fox say");
        assertThat(actual).isEqualTo(true);
    }
}
