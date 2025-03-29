/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonObject
 *  com.google.gson.annotations.Expose
 *  com.google.gson.annotations.SerializedName
 */
package pisi.unitedmeows.violentcat.shared.holders.shared.message.embed;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.embed.EmbedAuthor;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.embed.EmbedField;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.embed.EmbedMedia;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.embed.EmbedProvider;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.embed.EmbedType;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.embed.Footer;

public class Embed {
    private static final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    @Expose
    private String title;
    @Expose
    @SerializedName(value="type")
    private String typeName;
    private volatile EmbedType embedType;
    @Expose
    private String description;
    @Expose
    private int color;
    @Expose
    private Footer footer;
    @Expose
    private EmbedMedia image;
    @Expose
    private EmbedMedia thumbnail;
    @Expose
    private EmbedMedia video;
    @Expose
    private EmbedProvider provider;
    @Expose
    private EmbedAuthor author;
    @Expose
    private List<EmbedField> fields;

    protected Embed() {
    }

    public static Embed rich() {
        Embed embed = new Embed();
        embed.embedType = EmbedType.RICH;
        embed.typeName = embed.embedType.code();
        return embed;
    }

    public Embed footer(Consumer<Footer> _footer) {
        if (this.footer == null) {
            this.footer = new Footer();
        }
        _footer.accept(this.footer);
        return this;
    }

    public Embed image(Consumer<EmbedMedia> _image) {
        if (this.image == null) {
            this.image = new EmbedMedia();
        }
        _image.accept(this.image);
        return this;
    }

    public Embed thumbnail(Consumer<EmbedMedia> _thumbnail) {
        if (this.thumbnail == null) {
            this.thumbnail = new EmbedMedia();
        }
        _thumbnail.accept(this.thumbnail);
        return this;
    }

    public Embed video(Consumer<EmbedMedia> _video) {
        if (this.video == null) {
            this.video = new EmbedMedia();
        }
        _video.accept(this.video);
        return this;
    }

    public Embed provider(Consumer<EmbedProvider> _provider) {
        if (this.provider == null) {
            this.provider = new EmbedProvider();
        }
        _provider.accept(this.provider);
        return this;
    }

    public Embed author(Consumer<EmbedAuthor> _author) {
        if (this.author == null) {
            this.author = new EmbedAuthor();
        }
        _author.accept(this.author);
        return this;
    }

    public Embed title(String _title) {
        this.title = _title;
        return this;
    }

    public Embed description(String _description) {
        this.description = _description;
        return this;
    }

    public Embed color(int _color) {
        this.color = _color;
        return this;
    }

    public Embed addField(Consumer<EmbedField> _field) {
        if (this.fields == null) {
            this.fields = new ArrayList<EmbedField>();
        }
        EmbedField embedField = new EmbedField();
        _field.accept(embedField);
        this.fields.add(embedField);
        return this;
    }

    public List<EmbedField> fields() {
        return this.fields;
    }

    public String toString() {
        return gson.toJson((Object)this);
    }

    public JsonObject toObject() {
        return gson.toJsonTree((Object)this).getAsJsonObject();
    }
}

