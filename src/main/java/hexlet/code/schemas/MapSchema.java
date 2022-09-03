package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema {
    private Boolean required = false;
    private Boolean sizeof = false;
    private Integer size;

    public void required() {
        this.required = true;
    }
    public void sizeof (int size) {
        this.sizeof = true;
        this.size = size;
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

        return true;
    }
}
