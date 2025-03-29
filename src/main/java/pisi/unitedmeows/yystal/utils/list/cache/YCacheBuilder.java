/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.utils.list.cache;

import pisi.unitedmeows.yystal.utils.list.YCache;

public class YCacheBuilder<K, V> {
    private long deleteAfter;

    public static <K, V> YCacheBuilder<K, V> builder() {
        return new YCacheBuilder<K, V>();
    }

    public YCacheBuilder<K, V> deleteAfter(long _deleteAfter) {
        this.deleteAfter = _deleteAfter;
        return this;
    }

    public <K, V> YCache<K, V> build() {
        return new YCache(this.deleteAfter);
    }
}

