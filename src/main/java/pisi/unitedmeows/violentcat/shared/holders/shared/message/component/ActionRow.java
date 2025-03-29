/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.annotations.Expose
 */
package pisi.unitedmeows.violentcat.shared.holders.shared.message.component;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.component.Component;
import pisi.unitedmeows.violentcat.shared.stamp.OnlyLibCalls;

public class ActionRow
extends Component {
    @Expose
    private List<Component> components = new ArrayList<Component>();

    public static ActionRow create() {
        ActionRow actionRow = new ActionRow();
        actionRow.type = 1;
        return actionRow;
    }

    public ActionRow add(Component _component) {
        if (this.components.size() >= 5) {
            return this;
        }
        this.components.add(_component);
        return this;
    }

    @OnlyLibCalls
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("type", (Number)1);
        JsonArray array = new JsonArray();
        for (Component component : this.components) {
            array.add(component.toJson());
        }
        json.add("components", (JsonElement)array);
        return json;
    }
}

