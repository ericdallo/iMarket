package br.com.imarket.imarket.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.facebook.login.widget.LoginButton;

import br.com.imarket.imarket.R;
import br.com.imarket.imarket.font.Font;

public class FacebookButton extends LoginButton {

    private static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    public FacebookButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        int colorNormal = getResources().getColor(R.color.facebook);
        Drawable logo = getResources().getDrawable(R.drawable.facebook_logo);

        setCompoundDrawablesWithIntrinsicBounds(logo, null, null, null);
        setBackgroundColor(colorNormal);
        //getBackground().setColorFilter(colorNormal, PorterDuff.Mode.MULTIPLY);
        setBackgroundDrawable(getResources().getDrawable(R.drawable.white_roundcorner));
        setIncludeFontPadding(true);

        if ((attrs.getAttributeValue(ANDROID_SCHEMA, "fontFamily") == null) && (attrs.getAttributeValue(ANDROID_SCHEMA, "textStyle") == null)) {
            setTypeface(Font.museoSans(context, Typeface.NORMAL));
        }

        if (attrs.getAttributeValue(ANDROID_SCHEMA, "textSize") == null) {
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        }

        if (attrs.getAttributeValue(ANDROID_SCHEMA, "padding") == null) {
            int padding = getResources().getDimensionPixelSize(R.dimen.social_button_padding);
            setPadding(padding, padding, padding, padding);
        }

        if (attrs.getAttributeValue(ANDROID_SCHEMA, "drawablePadding") == null) {
            int padding = getResources().getDimensionPixelSize(R.dimen.social_button_padding);
            setCompoundDrawablePadding(padding);
        }

        setTextColor(Color.WHITE);
    }
}
