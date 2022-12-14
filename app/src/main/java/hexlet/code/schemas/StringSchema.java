package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
    @Override
    public StringSchema required() {
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
    public StringSchema minLength(int lenght) {
        addPredicate(x -> x.toString().length() >= lenght);
        return this;
    }
}
