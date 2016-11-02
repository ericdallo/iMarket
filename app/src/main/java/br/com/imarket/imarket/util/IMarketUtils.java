package br.com.imarket.imarket.util;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;

import br.com.imarket.imarket.R;

public class IMarketUtils {

    private final static Animation animation;

    static {
        animation = new TranslateAnimation(TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.ABSOLUTE, 100f, TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.ABSOLUTE, 0f);
        animation.setDuration(10000);
        animation.setRepeatCount(-1);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setInterpolator(new LinearInterpolator());
    }

    public static void animateHeader(Activity activity) {
        View headerView = activity.findViewById(R.id.lt_banner);
        headerView.setAnimation(animation);
    }
}
