package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
    @Override
    public final StringSchema required() {
        requiredOn();
        addPredicate(x -> {
            if (x instanceof String) {
                return !x.toString().isEmpty();
            }
            return false;
        });
        return this;
    }
    public  final  StringSchema contains(String subString) {
        addPredicate(x -> x.toString().contains(subString));
        return this;
    }
    public final StringSchema length(int lenght) {
        addPredicate(x -> x.toString().length() >= lenght);
        return this;
    }
}
