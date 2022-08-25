package hexlet.code.schemas;

public class StringSchema {

    private Boolean required = false;
    private int minLength;
    private String contains = "";
    public void required() {
        this.required = true;
    }
    public void minLength(int minLength) {
        this.minLength = minLength;
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
