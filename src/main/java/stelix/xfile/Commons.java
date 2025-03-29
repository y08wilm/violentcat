/*
 * Decompiled with CFR 0.152.
 */
package stelix.xfile;

import java.util.regex.Pattern;

public class Commons {
    private static String[] notSupportedNames = new String[]{"false", "true", "null"};
    private static final Pattern validIdentifierPattern = Pattern.compile("[^A-Za-z0-9]");

    public static void putSpace(StringBuilder stringBuilder, int count) {
        for (int i = 0; i < Math.max(0, count * 4); ++i) {
            stringBuilder.append(" ");
        }
    }

    public static String writeVar(Object obj) {
        if (obj instanceof String) {
            return "\"" + obj + "\"";
        }
        return obj.toString();
    }

    public static String removeQuotes(String data) {
        if (data.contains("\"")) {
            return data.replace("\"", "");
        }
        return data;
    }

    public static String clearName(String name) {
        if (validIdentifierPattern.matcher(name).find()) {
            return "\"" + name + "\"";
        }
        for (String check : notSupportedNames) {
            if (!name.toLowerCase().equalsIgnoreCase(check)) continue;
            return "\"" + name + "\"";
        }
        return name;
    }
}

