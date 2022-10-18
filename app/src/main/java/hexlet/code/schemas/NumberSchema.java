package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {
    @Override
    public NumberSchema required() {
        addPredicate(x -> {
            if (x instanceof Integer || x instanceof Double) {
                return true;
            }
            return false;
        });
        return this;
    }

    public NumberSchema positive() {
        addPredicate(x -> {
            if (x instanceof Integer) {
                return (int) x > 0;
            }
            if (x instanceof Double) {
                return (double) x > 0;
            }
            return false;
        });
        return this;
    }

    public NumberSchema range(int startRange, int endRange) {
        addPredicate(x -> {
            if (x instanceof Integer) {
                return (int) x >= startRange && (int) x <= endRange;
            }
            if (x instanceof Double) {
                return (double) x >= startRange && (double) x <= endRange;
            }
            return false;
        });
        return this;
    }
}
