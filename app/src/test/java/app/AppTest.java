package app;

import hexlet.code.Validator;
import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    private final Validator validator = new Validator();
    private final StringSchema stringSchema = validator.string();
    private final NumberSchema numberSchema = validator.number();
    private final MapSchema mapSchema = validator.map();

    private final Integer testNumber4 = 4;
    private final Integer testNumber5 = 5;
    private final Integer testNumber10 = 10;
    private final Integer testNumber11 = 11;
    private final Integer testNegativeNumber = -10;

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

        Boolean actual2 = numberSchema.isValid(Integer.parseInt("10"));
        assertThat(actual2).isEqualTo(true);

        Boolean actual3 = numberSchema.isValid("10");
        assertThat(actual3).isEqualTo(false);
    }

    @Test
    void testNumberSchema3() {
        Boolean actual1 = numberSchema.positive().isValid(Integer.parseInt("10"));
        assertThat(actual1).isEqualTo(true);

        Boolean actual2 = numberSchema.isValid(Integer.parseInt("-10"));
        assertThat(actual2).isEqualTo(false);
    }

    @Test
    void testNumberSchema4() {
        numberSchema.range(testNumber5, testNumber10);

        Boolean actual1 = numberSchema.isValid(testNumber5);
        assertThat(actual1).isEqualTo(true);

        Boolean actual2 = numberSchema.isValid(testNumber10);
        assertThat(actual2).isEqualTo(true);

        Boolean actual3 = numberSchema.isValid(testNumber4);
        assertThat(actual3).isEqualTo(false);

        Boolean actual4 = numberSchema.isValid(testNumber11);
        assertThat(actual4).isEqualTo(false);
    }

    @Test
    void testMapSchema1() {
        Boolean actual = mapSchema.isValid(null);
        assertThat(actual).isEqualTo(true);
    }

    @Test
    void testMapSchema2() {
        mapSchema.required();
        Boolean actual = mapSchema.isValid(null);
        assertThat(actual).isEqualTo(false);
    }
    @Test
    void testMapSchema3() {
        mapSchema.required();
        Boolean actual1 = mapSchema.isValid(null);
        assertThat(actual1).isEqualTo(false);

        Boolean actual2 = mapSchema.isValid(new HashMap());
        assertThat(actual2).isEqualTo(true);

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        Boolean actual3 = mapSchema.isValid(data);
        assertThat(actual3).isEqualTo(true);
    }

    @Test
    void testMapSchema4() {
        mapSchema.required();

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        mapSchema.sizeof(2);

        Boolean actual1 = mapSchema.isValid(data);
        assertThat(actual1).isEqualTo(false);

        data.put("key2", "value2");
        Boolean actual2 = mapSchema.isValid(data);
        assertThat(actual2).isEqualTo(true);
    }
    @Test
    void testMapSchemaShape1() {
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", validator.string().required());
        schemas.put("age", validator.number().positive());
        mapSchema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", testNumber10);
        Boolean actual1 = mapSchema.isValid(human1);
        assertThat(actual1).isEqualTo(true);
    }
    @Test
    void testMapSchemaShape2() {
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", validator.string().required());
        schemas.put("age", validator.number().positive());
        mapSchema.shape(schemas);
    }
    @Test
    void testMapSchemaShape3() {
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", validator.string().required());
        schemas.put("age", validator.number().positive());
        mapSchema.shape(schemas);

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        Boolean actual3 = mapSchema.isValid(human3);
        assertThat(actual3).isEqualTo(false);
    }
    @Test
    void testMapSchemaShape4() {
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", validator.string().required());
        schemas.put("age", validator.number().positive());
        mapSchema.shape(schemas);

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", testNegativeNumber);
        Boolean actual4 = mapSchema.isValid(human4);
        assertThat(actual4).isEqualTo(false);
    }
}
