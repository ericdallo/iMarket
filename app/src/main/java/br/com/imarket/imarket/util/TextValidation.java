package br.com.imarket.imarket.util;

import android.text.TextUtils;

public class TextValidation {

    public static boolean isBlank(String...texts) {
        return isTextsBlank(texts);
    }

    public static boolean isNotBlank(String...texts) {
        return !isTextsBlank(texts);
    }

    private static boolean isTextsBlank(String...texts) {
        if (texts.length == 0) {
            return true;
        }
        for (String text : texts) {
            if (TextUtils.isEmpty(text) || "".equals(text)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isNotEquals(String first, String second) {
        return !isTextEquals(first, second);
    }

    public static boolean isEquals(String first, String second) {
        return isTextEquals(first, second);
    }

    private static boolean isTextEquals(String first, String second) {
        return first != null && second != null && first.equals(second);
    }
}
