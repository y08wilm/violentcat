/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.google.gson.annotations.Expose
 *  com.google.gson.annotations.SerializedName
 */
package pisi.unitedmeows.violentcat.bot.etc;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.Message;
import pisi.unitedmeows.violentcat.shared.holders.shared.user.GuildMember;
import pisi.unitedmeows.violentcat.shared.stamp.OnlyLibCalls;
import pisi.unitedmeows.violentcat.utils.Jsons;

public class Interaction {
    @Expose
    protected int version;
    @Expose
    protected int type;
    @Expose
    protected String token;
    @Expose
    protected Message message;
    @Expose
    protected GuildMember member;
    @Expose
    protected String locale;
    @Expose
    protected String id;
    @Expose
    @SerializedName(value="guild_locale")
    protected String guildLocale;
    @Expose
    @SerializedName(value="guild_id")
    protected String guildID;
    protected String customID;
    protected int componentType;

    @OnlyLibCalls
    public void __setup(JsonObject object) {
        JsonObject data = object.get("data").getAsJsonObject();
        this.customID = Jsons.getString(data.get("custom_id"));
        this.componentType = Jsons.getInt(data.get("component_type"));
    }

    public int version() {
        return this.version;
    }

    public Interaction version(int _version) {
        this.version = _version;
        return this;
    }

    public int type() {
        return this.type;
    }

    public Interaction type(int _type) {
        this.type = _type;
        return this;
    }

    public String token() {
        return this.token;
    }

    public Interaction token(String _token) {
        this.token = _token;
        return this;
    }

    public Message message() {
        return this.message;
    }

    public Interaction message(Message _message) {
        this.message = _message;
        return this;
    }

    public GuildMember member() {
        return this.member;
    }

    public Interaction member(GuildMember _member) {
        this.member = _member;
        return this;
    }

    public String locale() {
        return this.locale;
    }

    public Interaction locale(String _locale) {
        this.locale = _locale;
        return this;
    }

    public String id() {
        return this.id;
    }

    public Interaction id(String _id) {
        this.id = _id;
        return this;
    }

    public String guildLocale() {
        return this.guildLocale;
    }

    public Interaction guildLocale(String _guildLocale) {
        this.guildLocale = _guildLocale;
        return this;
    }

    public String guildID() {
        return this.guildID;
    }

    public Interaction guildID(String _guildID) {
        this.guildID = _guildID;
        return this;
    }

    public String customID() {
        return this.customID;
    }

    public Interaction customID(String _customID) {
        this.customID = _customID;
        return this;
    }

    public int componentType() {
        return this.componentType;
    }

    public Interaction componentType(int _componentType) {
        this.componentType = _componentType;
        return this;
    }
}

