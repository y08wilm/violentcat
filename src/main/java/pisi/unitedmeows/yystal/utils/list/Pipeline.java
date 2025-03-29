/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.utils.list;

import java.util.Collection;
import java.util.LinkedHashMap;

public abstract class Pipeline<K, V> {
    protected LinkedHashMap<K, V> map = new LinkedHashMap();

    public abstract void putPost(K var1, V var2);

    public void put(K key, V value) {
        this.map.put(key, value);
        this.putPost(key, value);
    }

    public void putFirst(K key, V value) {
        LinkedHashMap oldMap = (LinkedHashMap)this.map.clone();
        this.map.clear();
        this.map.put(key, value);
        this.putPost(key, value);
        this.map.putAll(oldMap);
    }

    public V remove(K key) {
        return this.map.remove(key);
    }

    public boolean containsKey(K key) {
        return this.map.containsKey(key);
    }

    public boolean containsValue(V value) {
        return this.map.containsValue(value);
    }

    public void clear() {
        this.map.clear();
    }

    public Collection<V> values() {
        return this.map.values();
    }
}

