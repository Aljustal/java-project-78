package app;

import hexlet.code.Validator;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    private final Validator validator = new Validator();
    private final StringSchema stringSchema = validator.string();
    private final NumberSchema numberSchema = validator.number();
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

    @Test
    void testNumberSchema1() {
        Boolean actual = numberSchema.isValid(null);
        assertThat(actual).isEqualTo(true);
    }
    @Test
    void testNumberSchema2() {
        numberSchema.required();
        Boolean actual1 = numberSchema.isValid(null);
        assertThat(actual1).isEqualTo(false);

        Boolean actual2 = numberSchema.isValid(10);
        assertThat(actual2).isEqualTo(true);

        Boolean actual3 = numberSchema.isValid("10");
        assertThat(actual3).isEqualTo(false);
    }

    @Test
    void testNumberSchema3() {
        Boolean actual1 = numberSchema.positive().isValid(10);
        assertThat(actual1).isEqualTo(true);

        Boolean actual2 = numberSchema.isValid(-10);
        assertThat(actual2).isEqualTo(false);
    }

    @Test
    void testNumberSchema4() {
        numberSchema.range(5, 10);

        Boolean actual1 = numberSchema.isValid(5);
        assertThat(actual1).isEqualTo(true);

        Boolean actual2 = numberSchema.isValid(10);
        assertThat(actual2).isEqualTo(true);

        Boolean actual3 = numberSchema.isValid(4);
        assertThat(actual3).isEqualTo(false);

        Boolean actual4 = numberSchema.isValid(11);
        assertThat(actual4).isEqualTo(false);
    }
}
