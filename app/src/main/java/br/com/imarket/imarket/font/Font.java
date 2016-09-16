package br.com.imarket.imarket.font;

import android.content.Context;
import android.graphics.Typeface;

public class Font {

    private static Typeface amatic;
    private static Typeface museoSans;

    public static Typeface amatic(Context context) {
        if (amatic == null) {
            amatic = Typeface.createFromAsset(context.getAssets(), "fonts/Amatic-Bold.ttf");
        }
        return amatic;
    }
    public static Typeface museoSans(Context context) {
        if (museoSans == null) {
            museoSans = Typeface.createFromAsset(context.getAssets(), "fonts/museo_sans.ttf");
        }
        return museoSans;
    }
}
