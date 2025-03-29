package pisi.unitedmeows.yystal.clazz;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Holder {

    private Map<String, Object> values;
    private boolean initialized;

    public Holder() {
        values = new HashMap<>();
    }

    public void initialize() {
        for (Field field : getClass().getDeclaredFields()) {
            try {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                values.put(field.getName(), field.get(this));
            } catch (Exception ex) {

            }
        }
        initialized = true;
    }

    public <X> X get(String name) {
        if (!initialized) {
            initialize();
        }
        return (X) values.getOrDefault(name, null);
    }
}
