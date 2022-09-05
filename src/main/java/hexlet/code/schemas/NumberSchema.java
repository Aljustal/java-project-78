package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {
    private Boolean required = false;
    private Boolean positive = false;
    private Boolean range = false;
    private int startRange;
    private int endRange;

    public void required() {
        this.required = true;
    }

    public NumberSchema positive() {
        this.positive = true;
        return this;
    }
    public void range(int startRange, int endRange) {
        this.range = true;
        this.startRange = startRange;
        this.endRange = endRange;
    }

    @Override
    public Boolean isValid(Object obj) {
        Integer number;
        try {
            number = (Integer) obj;
        } catch (ClassCastException e) {
            return false;
        }

        if (required && number == null) {
            return false;
        }

        if (positive) {
            try {
                if (number < 0) {
                    return false;
                }
            } catch (NullPointerException e) {
                return true;
            }
        }

        if (range && (startRange > number || number > endRange)) {
            return false;
        }

        return true;
    }
}