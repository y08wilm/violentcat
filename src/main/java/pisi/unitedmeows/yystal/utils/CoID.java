/*
 * Decompiled with CFR 0.152.
 */
package pisi.unitedmeows.yystal.utils;

import java.util.Random;
import java.util.regex.Pattern;

public class CoID
implements Comparable<CoID> {
    public static final String ALL_UPPERCASE = "ABCDEFGHIJKLMNOPRSTUVYZXQ";
    public static final String DIGITS = "0123456789";
    public static final String ALL_LOWERCASE = "abcdefghijklmnoprstuvyzxq";
    public static final String SPECIAL_CHARS = "COMPLEX2173";
    private static final Pattern coidPattern = Pattern.compile("[COMPLEX2173]{2}\\$[A-Z][0-9][A-Z][0-9]\\-[a-z]{2}[0-9]\\-[COMPLEX2173][A-Z][a-z][A-Z]{3}[a-z]{2}\\-[0-9]{2}");
    private static Random random = new Random();
    private static final int COID_LENGTH = 23;
    private final String value;

    public CoID(String val) {
        this.value = val;
    }

    public String toString() {
        return this.value;
    }

    public static CoID generate() {
        StringBuilder builder = new StringBuilder();
        builder.append(CoID.nextSpecial());
        builder.append(CoID.nextSpecial());
        builder.append('$');
        builder.append(CoID.nextUpper());
        builder.append(CoID.nextDigit());
        builder.append(CoID.nextUpper());
        builder.append(CoID.nextDigit());
        builder.append('-');
        builder.append(CoID.nextLower());
        builder.append(CoID.nextLower());
        builder.append(CoID.nextDigit());
        builder.append('-');
        builder.append(CoID.nextSpecial());
        builder.append(CoID.nextUpper());
        builder.append(CoID.nextLower());
        builder.append(CoID.nextUpper());
        builder.append(CoID.nextUpper());
        builder.append(CoID.nextUpper());
        builder.append(CoID.nextLower());
        builder.append(CoID.nextLower());
        builder.append('-');
        builder.append(CoID.nextDigit());
        builder.append(CoID.nextDigit());
        return new CoID(builder.toString());
    }

    private static char nextUpper() {
        return ALL_UPPERCASE.charAt(random.nextInt(ALL_LOWERCASE.length()));
    }

    private static char nextLower() {
        return ALL_LOWERCASE.charAt(random.nextInt(ALL_LOWERCASE.length()));
    }

    private static char nextSpecial() {
        return SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length()));
    }

    private static char nextDigit() {
        return DIGITS.charAt(random.nextInt(DIGITS.length()));
    }

    @Deprecated
    public static boolean isLegal(String coid) {
        if (coid.length() != 23) {
            return false;
        }
        return coidPattern.matcher(coid).matches();
    }

    public static boolean isLegal(CoID coID) {
        return CoID.isLegal(coID.toString());
    }

    public static void setRandom(Random random) {
        CoID.random = random;
    }

    @Override
    public int compareTo(CoID o) {
        return this.toString().equals(o.toString()) ? 1 : 0;
    }

    public boolean same(CoID o2) {
        return this.toString().equals(o2.toString());
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        CoID coID = (CoID)o;
        return this.value != null ? this.value.equals(coID.value) : coID.value == null;
    }

    public int hashCode() {
        return this.value.hashCode();
    }
}

