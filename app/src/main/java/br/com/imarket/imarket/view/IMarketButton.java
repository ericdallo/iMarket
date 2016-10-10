package br.com.imarket.imarket.view;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import br.com.imarket.imarket.font.Font;

public class IMarketButton extends AppCompatButton {

    public IMarketButton(Context context) {
        super(context);
    }

    public IMarketButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        Font.applyCustomFont(this, context, attrs);
        this.setTransformationMethod(null);
    }

    public IMarketButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Font.applyCustomFont(this, context, attrs);
        this.setTransformationMethod(null);
    }

}