package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
    @Override
    public StringSchema required() {
        requiredOn();
        addPredicate(x -> {
            if (x instanceof String) {
                return !x.toString().isEmpty();
            }
            return false;
        });
        return this;
    }
    public StringSchema contains(String subString) {
        addPredicate(x -> x.toString().contains(subString));
        return this;
    }
    public StringSchema length(int lenght) {
        addPredicate(x -> x.toString().length() >= lenght);
        return this;
    }
}
