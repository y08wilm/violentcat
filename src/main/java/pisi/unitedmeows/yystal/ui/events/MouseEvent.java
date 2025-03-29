/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.ui.events;

import java.util.HashMap;
import java.util.Map;
import pisi.unitedmeows.yystal.clazz.delegate;

@FunctionalInterface
public interface MouseEvent
extends delegate {
    public void onMouseClick(Button var1, Action var2, int var3);

    public static enum Action {
        PRESS(1),
        RELEASE(0);

        private static Map<Integer, Action> map;
        int id;

        private Action(int _id) {
            this.id = _id;
        }

        public int id() {
            return this.id;
        }

        public static Action parse(int key) {
            return map.getOrDefault(key, null);
        }

        static {
            map = new HashMap<Integer, Action>();
            for (Action button : Action.values()) {
                map.put(button.id, button);
            }
        }
    }

    public static enum Button {
        MOUSE1(0),
        MOUSE2(1),
        MOUSE3(2),
        MOUSE4(3),
        MOUSE5(4),
        MOUSE6(5),
        MOUSE7(6),
        MOUSE8(7);

        private static Map<Integer, Button> map;
        int id;

        private Button(int _id) {
            this.id = _id;
        }

        public static Button parse(int key) {
            return map.getOrDefault(key, null);
        }

        static {
            map = new HashMap<Integer, Button>();
            for (Button button : Button.values()) {
                map.put(button.id, button);
            }
        }
    }
}

