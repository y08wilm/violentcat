/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.ui.utils.keyboard;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Locale;
import pisi.unitedmeows.yystal.ui.YWindow;

public class YKeyboardTextInput {
    private StringBuilder builder;
    private String tempString;
    private static HashMap<Integer, String> keyMap = new HashMap();
    private YWindow window;

    public YKeyboardTextInput(YWindow _window) {
        this.window = _window;
        this.builder = new StringBuilder();
    }

    public void press(int key) {
        System.out.println(key);
        if ((key == 109 || key == 259) && this.builder.length() != 0) {
            this.builder = new StringBuilder(this.builder.substring(0, this.builder.length() - 1));
            this.tempString = this.builder.toString();
            return;
        }
        boolean shiftPressed = this.window.keyManager().isPressed(340) || this.window.keyManager().isPressed(344);
        boolean upper = shiftPressed || Toolkit.getDefaultToolkit().getLockingKeyState(20);
        String keyText = "";
        switch (key) {
            case 32: {
                keyText = " ";
                break;
            }
            case 9: {
                keyText = "    ";
                break;
            }
            case 46: {
                keyText = shiftPressed ? ":" : ".";
                break;
            }
            case 56: {
                keyText = shiftPressed ? "(" : "8";
                break;
            }
            case 57: {
                keyText = shiftPressed ? ")" : "8";
                break;
            }
            case 55: {
                keyText = shiftPressed ? "/" : "7";
                break;
            }
            default: {
                keyText = KeyEvent.getKeyText(key);
                if (keyText.length() == 1) break;
                keyText = null;
            }
        }
        if (keyText == null) {
            keyText = keyMap.get(key);
        }
        if (keyText != null) {
            this.builder.append(upper ? keyText.toUpperCase(Locale.ROOT) : keyText.toLowerCase(Locale.ROOT));
        }
        this.tempString = this.builder.toString();
    }

    public String toString() {
        return this.tempString;
    }

    static {
        keyMap.put(39, "i");
    }
}

