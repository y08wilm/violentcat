/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 */
package pisi.unitedmeows.violentcat.shared.holders.shared.message.component;

import com.google.gson.JsonElement;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.component.Component;
import pisi.unitedmeows.violentcat.shared.stamp.OnlyLibCalls;
import pisi.unitedmeows.violentcat.utils.Jsons;

public class Button
extends Component {
    protected Button() {
    }

    public static Button create(String _label, int _style, String _customId) {
        Button component = new Button();
        component.type = 2;
        component.label = _label;
        component.style = _style;
        component.customId = _customId;
        return component;
    }

    @Override
    @OnlyLibCalls
    public JsonElement toJson() {
        return Jsons.BUILDER.toJsonTree((Object)this).getAsJsonObject();
    }
}

