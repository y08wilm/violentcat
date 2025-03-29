/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.ui.font;

import java.awt.Font;
import java.awt.Point;
import java.awt.font.GlyphVector;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.text.Bidi;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.WeakHashMap;
import pisi.unitedmeows.yystal.ui.font.GlyphCache;

public class StringCache {
    private final GlyphCache glyphCache;
    private final WeakHashMap<Key, Entry> stringCache = new WeakHashMap();
    private final WeakHashMap<String, Key> weakRefCache = new WeakHashMap();
    private final Key lookupKey = new Key();
    GlyphCache.Glyph[][] digitGlyphs = new GlyphCache.Glyph[4][];
    private boolean digitGlyphsReady = false;
    boolean antiAliasEnabled = false;
    private final Thread mainThread = Thread.currentThread();

    public StringCache() {
        this.glyphCache = new GlyphCache();
    }

    public void clear() {
        this.glyphCache.clear();
    }

    public Font setDefaultFont(String name, float fontSize, boolean antiAlias) {
        Font font = this.glyphCache.setDefaultFont(name, fontSize, antiAlias);
        this.antiAliasEnabled = antiAlias;
        this.weakRefCache.clear();
        this.stringCache.clear();
        this.cacheDightGlyphs();
        return font;
    }

    public Font setDefaultFont(InputStream stream, float fontSize, boolean antiAlias) {
        Font font = this.glyphCache.setDefaultFont(stream, fontSize, antiAlias);
        this.antiAliasEnabled = antiAlias;
        this.weakRefCache.clear();
        this.stringCache.clear();
        this.cacheDightGlyphs();
        return font;
    }

    public void setDefaultFont(Font font, float fontSize, boolean antiAlias) {
        this.glyphCache.setDefaultFont(font, fontSize, antiAlias);
        this.antiAliasEnabled = antiAlias;
        this.weakRefCache.clear();
        this.stringCache.clear();
        this.cacheDightGlyphs();
    }

    private void cacheDightGlyphs() {
        this.digitGlyphsReady = false;
        this.digitGlyphs[0] = this.cacheString((String)"0123456789").glyphs;
        this.digitGlyphs[1] = this.cacheString((String)"\u00a7l0123456789").glyphs;
        this.digitGlyphs[2] = this.cacheString((String)"\u00a7o0123456789").glyphs;
        this.digitGlyphs[3] = this.cacheString((String)"\u00a7l\u00a7o0123456789").glyphs;
        this.digitGlyphsReady = true;
    }

    public Entry cacheString(String str) {
        Entry entry = null;
        if (this.mainThread == Thread.currentThread()) {
            this.lookupKey.str = str;
            entry = this.stringCache.get(this.lookupKey);
        }
        if (entry == null) {
            char[] text = str.toCharArray();
            entry = new Entry();
            int length = this.stripColorCodes(entry, str, text);
            ArrayList<GlyphCache.Glyph> glyphList = new ArrayList<GlyphCache.Glyph>();
            entry.advance = this.layoutBidiString(glyphList, text, 0, length, entry.colors);
            entry.glyphs = new GlyphCache.Glyph[glyphList.size()];
            entry.glyphs = glyphList.toArray(entry.glyphs);
            Arrays.sort(entry.glyphs);
            int colorIndex = 0;
            int shift = 0;
            for (int glyphIndex = 0; glyphIndex < entry.glyphs.length; ++glyphIndex) {
                GlyphCache.Glyph glyph = entry.glyphs[glyphIndex];
                while (colorIndex < entry.colors.length && glyph.stringIndex + shift >= entry.colors[colorIndex].stringIndex) {
                    shift += 2;
                    ++colorIndex;
                }
                glyph.stringIndex += shift;
            }
            if (this.mainThread == Thread.currentThread()) {
                Key key = new Key();
                key.str = str;
                entry.keyRef = new WeakReference<Key>(key);
                this.stringCache.put(key, entry);
            }
        }
        if (this.mainThread == Thread.currentThread()) {
            Key oldKey = (Key)entry.keyRef.get();
            if (oldKey != null) {
                // empty if block
            }
            this.lookupKey.str = null;
        }
        return entry;
    }

    private int stripColorCodes(Entry cacheEntry, String str, char[] text) {
        int next;
        ArrayList<ColorCode> colorList = new ArrayList<ColorCode>();
        int start = 0;
        int shift = 0;
        int fontStyle = 0;
        int renderStyle = 0;
        int colorCode = -1;
        while ((next = str.indexOf(167, start)) != -1 && next + 1 < str.length()) {
            System.arraycopy(text, next - shift + 2, text, next - shift, text.length - next - 2);
            int code = "0123456789abcdefklmnor".indexOf(Character.toLowerCase(str.charAt(next + 1)));
            switch (code) {
                case 16: {
                    cacheEntry.obfuscated = true;
                    break;
                }
                case 17: {
                    fontStyle = (byte)(fontStyle | 1);
                    break;
                }
                case 18: {
                    renderStyle = (byte)(renderStyle | 2);
                    cacheEntry.specialRender = true;
                    break;
                }
                case 19: {
                    renderStyle = (byte)(renderStyle | 1);
                    cacheEntry.specialRender = true;
                    break;
                }
                case 20: {
                    fontStyle = (byte)(fontStyle | 2);
                    break;
                }
                case 21: {
                    fontStyle = 0;
                    renderStyle = 0;
                    colorCode = -1;
                    break;
                }
                default: {
                    if (code < 0) break;
                    if (cacheEntry.obfuscated) {
                        cacheEntry.obfuscated = false;
                    }
                    colorCode = (byte)code;
                    fontStyle = 0;
                    renderStyle = 0;
                }
            }
            ColorCode entry = new ColorCode();
            entry.stringIndex = next;
            entry.stripIndex = next - shift;
            entry.colorCode = (byte)colorCode;
            entry.fontStyle = (byte)fontStyle;
            entry.renderStyle = (byte)renderStyle;
            colorList.add(entry);
            start = next + 2;
            shift += 2;
        }
        cacheEntry.colors = new ColorCode[colorList.size()];
        cacheEntry.colors = colorList.toArray(cacheEntry.colors);
        return text.length - shift;
    }

    private float layoutBidiString(List<GlyphCache.Glyph> glyphList, char[] text, int start, int limit, ColorCode[] colors) {
        float advance = 0.0f;
        if (Bidi.requiresBidi(text, start, limit)) {
            Bidi bidi = new Bidi(text, start, null, 0, limit - start, -2);
            if (bidi.isRightToLeft()) {
                return this.layoutStyle(glyphList, text, start, limit, 1, advance, colors);
            }
            int runCount = bidi.getRunCount();
            byte[] levels = new byte[runCount];
            Object[] ranges = new Integer[runCount];
            for (int index = 0; index < runCount; ++index) {
                levels[index] = (byte)bidi.getRunLevel(index);
                ranges[index] = index;
            }
            Bidi.reorderVisually(levels, 0, ranges, 0, runCount);
            for (int visualIndex = 0; visualIndex < runCount; ++visualIndex) {
                int logicalIndex = (Integer)ranges[visualIndex];
                int layoutFlag = (bidi.getRunLevel(logicalIndex) & 1) == 1 ? 1 : 0;
                advance = this.layoutStyle(glyphList, text, start + bidi.getRunStart(logicalIndex), start + bidi.getRunLimit(logicalIndex), layoutFlag, advance, colors);
            }
            return advance;
        }
        return this.layoutStyle(glyphList, text, start, limit, 0, advance, colors);
    }

    private float layoutStyle(List<GlyphCache.Glyph> glyphList, char[] text, int start, int limit, int layoutFlags, float advance, ColorCode[] colors) {
        byte currentFontStyle = 0;
        int colorIndex = Arrays.binarySearch(colors, (Object)start);
        if (colorIndex < 0) {
            colorIndex = -colorIndex - 2;
        }
        while (start < limit) {
            int next = limit;
            while (colorIndex >= 0 && colorIndex < colors.length - 1 && colors[colorIndex].stripIndex == colors[colorIndex + 1].stripIndex) {
                ++colorIndex;
            }
            if (colorIndex >= 0 && colorIndex < colors.length) {
                currentFontStyle = colors[colorIndex].fontStyle;
            }
            while (++colorIndex < colors.length) {
                if (colors[colorIndex].fontStyle == currentFontStyle) continue;
                next = colors[colorIndex].stripIndex;
                break;
            }
            advance = this.layoutString(glyphList, text, start, next, layoutFlags, advance, currentFontStyle);
            start = next;
        }
        return advance;
    }

    private float layoutString(List<GlyphCache.Glyph> glyphList, char[] text, int start, int limit, int layoutFlags, float advance, int style) {
        if (this.digitGlyphsReady) {
            for (int index = start; index < limit; ++index) {
                if (text[index] < '0' || text[index] > '9') continue;
                text[index] = 48;
            }
        }
        while (start < limit) {
            Font font = this.glyphCache.lookupFont(text, start, limit, style);
            int next = font.canDisplayUpTo(text, start, limit);
            if (next == -1) {
                next = limit;
            }
            if (next == start) {
                ++next;
            }
            advance = this.layoutFont(glyphList, text, start, next, layoutFlags, advance, font);
            start = next;
        }
        return advance;
    }

    private float layoutFont(List<GlyphCache.Glyph> glyphList, char[] text, int start, int limit, int layoutFlags, float advance, Font font) {
        if (this.mainThread == Thread.currentThread()) {
            this.glyphCache.cacheGlyphs(font, text, start, limit, layoutFlags);
        }
        GlyphVector vector = this.glyphCache.layoutGlyphVector(font, text, start, limit, layoutFlags);
        GlyphCache.Glyph glyph = null;
        int numGlyphs = vector.getNumGlyphs();
        for (int index = 0; index < numGlyphs; ++index) {
            Point position = vector.getGlyphPixelBounds(index, null, advance, 0.0f).getLocation();
            if (glyph != null) {
                glyph.advance = position.x - glyph.x;
            }
            glyph = new GlyphCache.Glyph();
            glyph.stringIndex = start + vector.getGlyphCharIndex(index);
            glyph.texture = this.glyphCache.lookupGlyph(font, vector.getGlyphCode(index));
            glyph.x = position.x;
            glyph.y = position.y;
            glyphList.add(glyph);
        }
        advance = (float)((double)advance + vector.getGlyphPosition(numGlyphs).getX());
        if (glyph != null) {
            glyph.advance = advance - (float)glyph.x;
        }
        return advance;
    }

    static class ColorCode
    implements Comparable<Integer> {
        public static final byte UNDERLINE = 1;
        public static final byte STRIKETHROUGH = 2;
        public int stringIndex;
        public int stripIndex;
        public byte fontStyle;
        public byte colorCode;
        public byte renderStyle;

        ColorCode() {
        }

        @Override
        public int compareTo(Integer i) {
            return Integer.compare(this.stringIndex, i);
        }
    }

    public static class Entry {
        public WeakReference<Key> keyRef;
        public float advance;
        public GlyphCache.Glyph[] glyphs;
        public ColorCode[] colors;
        public boolean specialRender;
        public boolean obfuscated;
    }

    private static class Key {
        public String str;

        private Key() {
        }

        public int hashCode() {
            int code = 0;
            int length = this.str.length();
            boolean colorCode = false;
            for (int index = 0; index < length; ++index) {
                int c = this.str.charAt(index);
                if (c >= 48 && c <= 57 && !colorCode) {
                    c = 48;
                }
                code = code * 31 + c;
                colorCode = c == 167;
            }
            return code;
        }

        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }
            String other = o.toString();
            int length = this.str.length();
            if (length != other.length()) {
                return false;
            }
            boolean colorCode = false;
            for (int index = 0; index < length; ++index) {
                char c2;
                char c1 = this.str.charAt(index);
                if (c1 != (c2 = other.charAt(index)) && (c1 < '0' || c1 > '9' || c2 < '0' || c2 > '9' || colorCode)) {
                    return false;
                }
                colorCode = c1 == '\u00a7';
            }
            return true;
        }

        public String toString() {
            return this.str;
        }
    }
}

