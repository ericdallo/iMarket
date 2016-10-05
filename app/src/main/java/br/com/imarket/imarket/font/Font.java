package br.com.imarket.imarket.font;

import android.content.Context;
import android.graphics.Typeface;

import static android.graphics.Typeface.BOLD;

public class Font {

    private final static String FONTS_PATH = "fonts/";

    private static Typeface amatic;
    private static Typeface museoSans;
    private static Typeface museoSansBold;

    public static Typeface amatic(Context context) {
        if (amatic == null) {
            amatic = Typeface.createFromAsset(context.getAssets(), FONTS_PATH + "Amatic-Bold.ttf");
        }
        return amatic;
    }
    public static Typeface museoSans(Context context, int...textStyle) {
        if (museoSans == null) {
            museoSans = Typeface.createFromAsset(context.getAssets(), FONTS_PATH + "museo-sans.ttf");
        }
        if (museoSansBold == null) {
            museoSansBold = Typeface.createFromAsset(context.getAssets(), FONTS_PATH + "museo-sans-bold.ttf");
        }
        if (textStyle.length != 0 && textStyle[0] == BOLD) {
            return museoSansBold;
        }
        return museoSans;
    }
}
