package hexlet.code.schemas;
import java.util.Map;

public class MapSchema extends BaseSchema {
    private Boolean required = false;
    private Boolean sizeof = false;
    private Boolean shape = false;
    private Map<String, BaseSchema> shapeMap;

    private Integer size;

    public void required() {
        this.required = true;
    }
    public void sizeof(int size) {
        this.sizeof = true;
        this.size = size;
    }
    public void shape(Map map) {
        this.shape = true;
        this.shapeMap = map;
    }

    @Override
    public Boolean isValid(Object obj) {

        var map = (Map) obj;

        if (required && map == null) {
            return false;
        }

        if (sizeof && map.size() != size) {
            return false;
        }

        if (shape) {
            for (var el: shapeMap.entrySet()) {
                var value = map.get(el.getKey());
                var shema = el.getValue().isValid(value);

                if (!shema) {
                    return false;
                }
            }
        }

        return true;
    }
}
