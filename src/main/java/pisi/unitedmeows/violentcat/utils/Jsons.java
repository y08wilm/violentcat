/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonNull
 *  com.google.gson.JsonParser
 */
package pisi.unitedmeows.violentcat.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParser;

public class Jsons {
    public static final Gson BUILDER = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    public static final JsonParser parser = new JsonParser();

    public static String getString(JsonElement jsonElement) {
        return jsonElement == JsonNull.INSTANCE ? null : jsonElement.getAsString();
    }

    public static int getInt(JsonElement jsonElement) {
        return jsonElement == JsonNull.INSTANCE ? -1 : jsonElement.getAsInt();
    }

    public static long getLong(JsonElement jsonElement) {
        return jsonElement == JsonNull.INSTANCE ? -1L : jsonElement.getAsLong();
    }

    public static boolean getBoolean(JsonElement jsonElement) {
        return jsonElement == JsonNull.INSTANCE ? null : Boolean.valueOf(jsonElement.getAsBoolean());
    }
}

