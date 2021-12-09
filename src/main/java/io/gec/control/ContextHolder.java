package io.gec.control;

import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ContextHolder {

    protected final Map<String, Object> data = new HashMap<>();

    public void clear() {
        data.clear();
    }
    
    public void put(String key, Object payload) {
        data.put(key, payload);
    }

    public Object get(String key) {
        return data.get(key);
    }

    public boolean containsKey(String key) {
        return data.containsKey(key);
    }

    public Object remove(String key) {
        return data.remove(key);
    }
    
}
