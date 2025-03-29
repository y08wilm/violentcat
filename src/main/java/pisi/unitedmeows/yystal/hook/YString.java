/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.hook;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import pisi.unitedmeows.yystal.clazz.HookClass;

public class YString
extends HookClass<StringBuilder> {
    public static final String EMPTY_R = "";
    public static final YString EMPTY = new YString("");
    private static final Pattern INLINE_VAR_PATTERN = Pattern.compile("\\#\\[([a-zA-Z_][a-zA-Z0-9_]*).([a-zA-Z_][a-zA-Z0-9_]*)(\\(\\))?\\]");
    private boolean changed;
    private String currentValue;
    private List<Object> holders = new LinkedList<Object>();
    private Map<String, Object> entities = new LinkedHashMap<String, Object>();

    public YString(StringBuilder stringBuilder) {
        this.hooked = stringBuilder;
        this.changed = true;
    }

    public YString(String initial) {
        this.hooked = new StringBuilder(initial);
        this.changed = true;
    }

    public YString add(String string) {
        this.changed = true;
        ((StringBuilder)this.hooked).append(string);
        return this;
    }

    public YString add(YString string) {
        return this.add(string.toString());
    }

    public YString replaceFirst(char character, String toReplace) {
        int lastChar = 89;
        int lastIndex = 0;
        String text = this.currentValue();
        StringBuilder stringBuilder = new StringBuilder();
        boolean replaced = false;
        for (int i = 0; i < text.length(); ++i) {
            char currentChar = text.charAt(i);
            if (currentChar == '\\' && text.length() > i + 1) {
                if (text.charAt(i + 1) != character) {
                    stringBuilder.append(text.charAt(i));
                }
            } else {
                if (lastChar != 92 && currentChar == character) {
                    stringBuilder.append(toReplace);
                    lastIndex = i;
                    replaced = true;
                    break;
                }
                stringBuilder.append(text.charAt(i));
            }
            lastChar = text.charAt(i);
        }
        if (replaced && lastIndex + 1 < text.length()) {
            stringBuilder.append(text.substring(lastIndex + 1));
        }
        return new YString(stringBuilder);
    }

    public String substring(int start) {
        return ((StringBuilder)this.hooked).substring(start);
    }

    public YString substringY(int start) {
        return new YString(((StringBuilder)this.hooked).substring(start));
    }

    public YString substringY(int start, int end) {
        return new YString(((StringBuilder)this.hooked).substring(start, end));
    }

    public YString replace(String text, String toReplace) {
        this.hooked = new StringBuilder(((StringBuilder)this.hooked).toString().replaceAll(text, toReplace));
        this.changed = true;
        return this;
    }

    public YString remove(String text) {
        return this.replace(text, EMPTY_R);
    }

    public String substring(int start, int end) {
        return ((StringBuilder)this.hooked).substring(start, end);
    }

    public List<String> lines() {
        String temp = this.currentValue().replace("\r\n", "\n");
        if (!temp.contains("\n")) {
            return Collections.singletonList(this.currentValue());
        }
        return Arrays.asList(temp.split("\n"));
    }

    public String toString() {
        String value = this.currentValue();
        if (this.changed) {
            Matcher matcher;
            while ((matcher = INLINE_VAR_PATTERN.matcher(value)).find()) {
                String entityName = matcher.group(1);
                String variable = matcher.group(2);
                Object entity = this.entities.getOrDefault(entityName, null);
                if (entity == null) continue;
                try {
                    Object data = matcher.group().contains("()") ? entity.getClass().getDeclaredMethod(variable, new Class[0]).invoke(entity, new Object[0]) : entity.getClass().getDeclaredField(variable).get(entity);
                    value = matcher.replaceFirst(data.toString());
                }
                catch (Exception e) {
                    e.printStackTrace();
                    value = matcher.replaceFirst("?");
                }
            }
            StringBuilder builder = new StringBuilder();
            int holderIndex = 0;
            boolean escapeNext = false;
            for (char character : value.toCharArray()) {
                if (character == '\\') {
                    escapeNext = true;
                } else {
                    if (character == '^') {
                        if (escapeNext) {
                            builder.deleteCharAt(builder.length() - 1);
                        } else {
                            builder.append(this.holders.get(holderIndex++));
                            continue;
                        }
                    }
                    escapeNext = false;
                }
                builder.append(character);
            }
            value = builder.toString();
        }
        return value;
    }

    protected String currentValue() {
        return this.changed ? (this.currentValue = ((StringBuilder)this.hooked).toString()) : this.currentValue;
    }

    public YString entity(String name, Object entity) {
        this.entities.put(name, entity);
        return this;
    }

    public YString put(Object data) {
        this.holders.add(data);
        return this;
    }
}

