/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package pisi.unitedmeows.violentcat.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.utils.Jsons;

public class GsonWrap {
    private static final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    private JsonObject _object;
    private Object result;

    public static GsonWrap parse(Class<?> type, String fromJson) {
        return new GsonWrap().convert(type, Jsons.parser.parse(fromJson).getAsJsonObject());
    }

    public static GsonWrap parse(Class<?> type, JsonObject fromJson) {
        return new GsonWrap().convert(type, fromJson);
    }

    public GsonWrap convert(Class<?> type, JsonObject object) {
        this._object = object;
        try {
            this.result = type.newInstance();
            this.result = gson.fromJson((JsonElement)object, this.result.getClass());
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return this;
    }

    public JsonObject data() {
        return this._object;
    }

    public <X> X result() {
        return (X)this.result;
    }
}

