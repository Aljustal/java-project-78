package hexlet.code.schemas;
import java.util.Map;

public final class MapSchema extends BaseSchema {
    @Override
    public MapSchema required() {
        requiredOn();
        addPredicate(x -> x instanceof Map);
        return this;
    }
    public MapSchema sizeof(int size) {
        addPredicate(map -> ((Map<?, ?>) map).size() == size);
        return this;
    }
    public void shape(final Map<String, BaseSchema> map) {
        addPredicate(x -> {
            for (Map.Entry<String, BaseSchema> entry : map.entrySet()) {
                if (!entry.getValue().isValid(((Map<?, ?>) x).get(entry.getKey()))) {
                    return false;
                }
            }
            return true;
        });
    }
}
