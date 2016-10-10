package br.com.imarket.imarket.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import br.com.imarket.imarket.font.Font;

public class IMarketTextView extends TextView {

    public IMarketTextView(Context context) {
        super(context);
    }

    public IMarketTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Font.applyCustomFont(this, context, attrs);
    }

    public IMarketTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Font.applyCustomFont(this, context, attrs);
    }

}