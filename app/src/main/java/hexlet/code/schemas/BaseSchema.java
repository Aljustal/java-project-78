package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract  class BaseSchema {

    public abstract BaseSchema required();
    private final List<Predicate> predicateList  = new ArrayList<>();
    final void addPredicate(Predicate predicate) {
        predicateList.add(predicate);
    }
    public final Boolean isValid(Object obj) {
        for (Predicate predicate: predicateList) {
            if (!predicate.test(obj)) {
                return false;
            }
        }
        return true;
    }
}
