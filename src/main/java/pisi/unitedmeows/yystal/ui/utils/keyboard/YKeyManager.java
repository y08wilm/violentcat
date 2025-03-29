/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.ui.utils.keyboard;

import java.util.HashMap;
import java.util.Map;

public class YKeyManager {
    private Map<Integer, Boolean> keyMap = new HashMap<Integer, Boolean>();

    public boolean isPressed(int key) {
        return this.keyMap.containsKey(key);
    }

    public void press(int key) {
        this.keyMap.put(key, true);
    }

    public void release(int key) {
        this.keyMap.remove(key);
    }
}

