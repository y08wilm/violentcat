/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package pisi.unitedmeows.yystal.ui.font;

import java.awt.Font;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.lwjgl.opengl.GL11;
import pisi.unitedmeows.yystal.ui.font.GlyphCache;
import pisi.unitedmeows.yystal.ui.font.StringCache;

public class TTFRenderer {
    public final Map<Float, StringCache> caches = new HashMap<Float, StringCache>();
    public final float defaultSize;
    private Font font;
    private boolean antiAlias;

    public TTFRenderer(String name, float size, boolean _antiAlias) {
        this.defaultSize = size;
        this.antiAlias = _antiAlias;
        StringCache cache = new StringCache();
        this.font = cache.setDefaultFont(name, size, _antiAlias);
    }

    public TTFRenderer(Font font, float size, boolean _antiAlias) {
        this.defaultSize = size;
        this.font = font;
        this.antiAlias = _antiAlias;
    }

    public TTFRenderer(InputStream stream, float size, boolean _antiAlias) {
        this.defaultSize = size;
        StringCache cache = new StringCache();
        this.font = cache.setDefaultFont(stream, size, _antiAlias);
        this.antiAlias = _antiAlias;
    }

    public float drawStringCentered(String text, double x, double y, int color, float size) {
        float width = this.width(text, size);
        this.drawString(text, x - (double)(width / 2.0f), y, color, size);
        return (float)(x + (double)(width / 2.0f));
    }

    public float drawStringCentered(String text, double x, double y, int color, float size, DrawType type) {
        float width = this.width(text, size);
        this.drawString(text, x - (double)(width / 2.0f), y, color, size, type);
        return (float)(x + (double)(width / 2.0f));
    }

    public float drawString(String text, double x, double y, int color, float size, DrawType type) {
        switch (type) {
            case NORMAL: {
                return this.drawString(text, x, y, color, size);
            }
            case SHADOW_THIN: {
                this.drawString(text, x + 1.0, y + 1.0, color & 0xFF000000, size);
                return this.drawString(text, x, y, color, size);
            }
            case SHADOW_THICK: {
                this.drawString(text, x - 1.0, y - 1.0, color & 0xFF000000, size);
                this.drawString(text, x - 1.0, y + 1.0, color & 0xFF000000, size);
                this.drawString(text, x + 1.0, y - 1.0, color & 0xFF000000, size);
                this.drawString(text, x + 1.0, y + 1.0, color & 0xFF000000, size);
                return this.drawString(text, x, y, color, size);
            }
        }
        return 0.0f;
    }

    public float drawString(String text, double x, double y, int color, float size) {
        if (text == null) {
            return 0.0f;
        }
        text = text.replace("\u0000", "null_char");
        x -= 1.0;
        if (color == 0x20FFFFFF) {
            color = 0xFFFFFF;
        }
        if ((color & 0xFC000000) == 0) {
            color |= 0xFF000000;
        }
        StringCache current = this.stringCache(size);
        StringCache.Entry entry = current.cacheString(text);
        double antiAlias = 2.0;
        x *= 2.0;
        y = (y + 3.0) * 2.0;
        GL11.glPushMatrix();
        GL11.glScaled((double)0.5, (double)0.5, (double)0.5);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)3553);
        GL11.glColor4f((float)((float)(color >> 16 & 0xFF) / 255.0f), (float)((float)(color >> 8 & 0xFF) / 255.0f), (float)((float)(color & 0xFF) / 255.0f), (float)((float)(color >> 24 & 0xFF) / 255.0f));
        for (int glyphIndex = 0; glyphIndex < entry.glyphs.length; ++glyphIndex) {
            GlyphCache.Glyph glyph = entry.glyphs[glyphIndex];
            GlyphCache.Entry texture = glyph.texture;
            float factor = 0.5f;
            int glyphX = glyph.x;
            char c = text.charAt(glyph.stringIndex);
            if (c >= '0' && c <= '9') {
                int oldWidth = texture.width;
                texture = current.digitGlyphs[0][c - 48].texture;
                int newWidth = texture.width;
                glyphX += oldWidth - newWidth >> 1;
            }
            float x1 = (float)glyphX / 0.5f;
            float x2 = (float)(glyphX + texture.width) / 0.5f;
            float y1 = (float)glyph.y / 0.5f;
            float y2 = (float)(glyph.y + texture.height) / 0.5f;
            int r = color >> 16 & 0xFF;
            int g = color >> 8 & 0xFF;
            int b = color & 0xFF;
            int a = color >> 24 & 0xFF;
            float minU = texture.u1;
            float minV = texture.v1;
            float maxU = texture.u2;
            float maxV = texture.v2;
            float calcX1 = (float)(x + (double)x1);
            float calcY1 = (float)(y + (double)y1);
            float calcX2 = (float)(x + (double)x2);
            float calcY2 = (float)(y + (double)y2);
            GL11.glBindTexture((int)3553, (int)texture.textureName);
            GL11.glBegin((int)7);
            GL11.glTexCoord2f((float)minU, (float)minV);
            GL11.glVertex2d((double)calcX1, (double)calcY1);
            GL11.glTexCoord2f((float)minU, (float)maxV);
            GL11.glVertex2d((double)calcX1, (double)calcY2);
            GL11.glTexCoord2f((float)maxU, (float)maxV);
            GL11.glVertex2d((double)calcX2, (double)calcY2);
            GL11.glTexCoord2f((float)maxU, (float)minV);
            GL11.glVertex2d((double)calcX2, (double)calcY1);
            GL11.glEnd();
        }
        GL11.glDisable((int)3042);
        GL11.glHint((int)3155, (int)4352);
        GL11.glPopMatrix();
        return (float)x;
    }

    public float height(String text, float size) {
        if (text == null || text.isEmpty()) {
            return 0.0f;
        }
        StringCache.Entry entry = this.stringCache(size).cacheString(text);
        float biggestDif = -1.0f;
        for (int glyphIndex = 0; glyphIndex < entry.glyphs.length; ++glyphIndex) {
            GlyphCache.Glyph glyph = entry.glyphs[glyphIndex];
            GlyphCache.Entry entry2 = glyph.texture;
            float height = entry2.height;
            if (!(height > biggestDif)) continue;
            biggestDif = height;
        }
        return biggestDif * this.defaultSize;
    }

    public float width(String text, float size) {
        if (text == null) {
            return 0.0f;
        }
        text = text.replace("\u0000", "null_char");
        StringCache current = this.stringCache(size);
        StringCache.Entry entry = current.cacheString(text);
        double antiAlias = (double)size * 2.0;
        return entry.advance;
    }

    public StringCache stringCache(float size) {
        StringCache current = this.caches.getOrDefault(Float.valueOf(size), null);
        if (current == null) {
            System.out.println("Non existent size creating one " + size);
            current = new StringCache();
            current.setDefaultFont(this.font, size, this.antiAlias);
            this.caches.put(Float.valueOf(size), current);
        }
        return current;
    }

    public Float defaultSize() {
        return Float.valueOf(this.defaultSize);
    }

    public static enum DrawType {
        NORMAL,
        SHADOW_THIN,
        SHADOW_THICK;

    }
}

