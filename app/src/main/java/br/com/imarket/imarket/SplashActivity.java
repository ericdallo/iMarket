package br.com.imarket.imarket;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.util.Log;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

import br.com.imarket.imarket.images.Images;

public class SplashActivity extends AwesomeSplash {


    private Intent nextActivity;

    @Override
    public void initSplash(ConfigSplash configSplash) {
        setupSplash(configSplash);

        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean isGpsDisabled = true;

        try {
            isGpsDisabled = !manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {
            Log.e("SystemError", "Cannot retrieve gps information");
        }

        if (isGpsDisabled) {
            nextActivity = new Intent(this, WelcomeActivity.class);
        } else {
            nextActivity = new Intent(this, SearchActivity.class);
        }
    }

    @Override
    public void animationsFinished() {
        startActivity(nextActivity);
        finish();
    }

    private void setupSplash(ConfigSplash configSplash) {
        configSplash.setBackgroundColor(R.color.colorPrimaryDark);
        configSplash.setAnimCircularRevealDuration(1500);
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM);

        //Customize Path
        configSplash.setPathSplash(Images.IMARKET_LOGO);
        configSplash.setPathSplashStrokeSize(3);
        configSplash.setPathSplashStrokeColor(R.color.white);
        configSplash.setPathSplashFillColor(R.color.fillwhite);
        configSplash.setOriginalHeight(600);
        configSplash.setOriginalWidth(600);
        configSplash.setAnimPathStrokeDrawingDuration(1000);
        configSplash.setAnimPathFillingDuration(1000);


        //Customize Title
        configSplash.setTitleSplash("iMarket");
        configSplash.setTitleTextColor(R.color.white);
        configSplash.setTitleTextSize(35f);
        configSplash.setAnimTitleDuration(1000);
        configSplash.setAnimTitleTechnique(Techniques.FadeInUp);
        configSplash.setTitleFont("fonts/museo-sans-bold.ttf");
    }
}
