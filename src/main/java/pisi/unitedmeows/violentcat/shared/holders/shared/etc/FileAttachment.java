/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package pisi.unitedmeows.violentcat.shared.holders.shared.etc;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.File;
import pisi.unitedmeows.violentcat.utils.Jsons;

public class FileAttachment {
    protected File file;
    protected String name;
    protected String description;

    protected FileAttachment() {
    }

    public static FileAttachment create() {
        return new FileAttachment();
    }

    public String toString(int id) {
        return Jsons.BUILDER.toJson((JsonElement)this.toObject(id));
    }

    public FileAttachment file(File _file) {
        this.file = _file;
        return this;
    }

    public FileAttachment name(String _name) {
        this.name = _name;
        return this;
    }

    public FileAttachment description(String _description) {
        this.description = _description;
        return this;
    }

    public String name() {
        return this.name;
    }

    public String description() {
        return this.description;
    }

    public File file() {
        return this.file;
    }

    public JsonObject toObject(int id) {
        JsonObject json = new JsonObject();
        json.addProperty("id", (Number)id);
        json.addProperty("filename", this.name);
        json.addProperty("description", this.description);
        return json;
    }
}

