package net.giuseppe.wine_shop.common.util;

public abstract class StringUtil {

    public static String removeUnderline(String text) {
        return text.replaceAll("_", " ");
    }
}
