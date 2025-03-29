/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.utils.list.cache;

public class YCacheNode<K, V> {
    private K key;
    private V value;
    private YCacheNode<K, V> next;
    private YCacheNode<K, V> before;

    public YCacheNode() {
    }

    public YCacheNode(K _key, V _value) {
        this.key = _key;
        this.value = _value;
    }

    public K key() {
        return this.key;
    }

    public V value() {
        return this.value;
    }

    public YCacheNode key(K _key) {
        this.key = _key;
        return this;
    }

    public YCacheNode value(V _value) {
        this.value = _value;
        return this;
    }

    public YCacheNode<K, V> next() {
        return this.next;
    }

    public YCacheNode next(YCacheNode<K, V> _next) {
        this.next = _next;
        return this;
    }

    public YCacheNode<K, V> before() {
        return this.before;
    }

    public YCacheNode before(YCacheNode<K, V> _before) {
        this.before = _before;
        return this;
    }
}

