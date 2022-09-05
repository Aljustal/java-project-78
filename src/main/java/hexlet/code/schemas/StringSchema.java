package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
    private Boolean required = false;
    private int minLength;
    private String contains = "";
    public StringSchema required() {
        this.required = true;
        return this;
    }
    public void minLength(int length) {
        this.minLength = length;
    }
    public StringSchema contains(String str) {
        this.contains = str;
        return this;
    }
    @Override
    public Boolean isValid(Object obj) {

        String str = (String) obj;

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
