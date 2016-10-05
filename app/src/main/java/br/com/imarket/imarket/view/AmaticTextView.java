package br.com.imarket.imarket.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import br.com.imarket.imarket.font.Font;

public class AmaticTextView extends TextView{

    public AmaticTextView(Context context) {
        super(context);
        this.setTypeface(Font.amatic(context));
    }

    public AmaticTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Font.amatic(context));
    }

    public AmaticTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setTypeface(Font.amatic(context));
    }
}
