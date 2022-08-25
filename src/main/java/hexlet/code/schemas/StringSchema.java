package hexlet.code.schemas;

public class StringSchema {

    private Boolean required = false;
    private int minLength;
    private String contains = "";
    public final void required() {
        this.required = true;
    }
    public final void minLength(int length) {
        this.minLength = length;
    }
    public final StringSchema contains(String str) {
        this.contains = str;
        return this;
    }

    public final Boolean isValid(String str) {

        if (required && (str == null || str.isEmpty())) {
            return false;
        }

        if (str.length() < minLength) {
            return false;
        }

        if (!str.contains(contains)) {
            return false;
        }

        return true;
    }
}
