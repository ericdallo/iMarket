package br.com.imarket.imarket;

import android.content.Intent;

import com.daimajia.androidanimations.library.Techniques;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

import br.com.imarket.imarket.images.Images;

import static br.com.imarket.imarket.util.LocationUtil.isGPSEnabled;

public class SplashActivity extends AwesomeSplash {

    private Intent nextActivity;

    @Override
    public void initSplash(ConfigSplash configSplash) {
        setupSplash(configSplash);

        if (isGPSEnabled(this)) {
            nextActivity = new Intent(this, MainActivity.class);
        } else {
            nextActivity = new Intent(this, LocationActivity.class);
        }
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplicationContext());
    }

    @Override
    public void animationsFinished() {
        startActivity(nextActivity);
        finish();
    }

    private void setupSplash(ConfigSplash configSplash) {
        configSplash.setBackgroundColor(R.color.colorPrimaryDark);
        configSplash.setAnimCircularRevealDuration(1000);
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM);

        //Customize Path
        configSplash.setPathSplash(Images.IMARKET_LOGO);
        configSplash.setPathSplashStrokeSize(3);
        configSplash.setPathSplashStrokeColor(R.color.white);
        configSplash.setPathSplashFillColor(R.color.fillwhite);
        configSplash.setOriginalHeight(600);
        configSplash.setOriginalWidth(600);
        configSplash.setAnimPathStrokeDrawingDuration(500);
        configSplash.setAnimPathFillingDuration(500);


        //Customize Title
        configSplash.setTitleSplash("iMarket");
        configSplash.setTitleTextColor(R.color.white);
        configSplash.setTitleTextSize(35f);
        configSplash.setAnimTitleDuration(1000);
        configSplash.setAnimTitleTechnique(Techniques.FadeInUp);
        configSplash.setTitleFont("fonts/museo-sans-bold.ttf");
    }
}
