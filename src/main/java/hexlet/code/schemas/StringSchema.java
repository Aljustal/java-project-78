package hexlet.code.schemas;

public class StringSchema {

    Boolean required = false;
    int minLength;
    String contains = "";
    public StringSchema required() {
        this.required = true;
        return this;
    }
    public StringSchema minLength(int minLength) {
        this.minLength = minLength;
        return this;
    }
    public StringSchema contains(String contains) {
        this.contains = contains;
        return this;
    }

    public Boolean isValid(String str) {

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
