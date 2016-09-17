package br.com.imarket.imarket;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.imarket.imarket.font.Font;
import br.com.imarket.imarket.util.LocationUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static br.com.imarket.imarket.util.LocationUtil.isGPSEnabled;

public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.tv_imarket)
    private TextView tvImarket;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configure_location);
        ButterKnife.bind(this);

        tvImarket.setTypeface(Font.amatic(this));
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isGPSEnabled(this)) {

        } else {

        }
    }

    @OnClick(R.id.bt_activate_location)
    public void activateLocation() {
        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
    }

    @OnClick(R.id.bt_later)
    public void later(View view) {
        startActivity(new Intent(view.getContext(), SearchActivity.class));
        finish();
    }
}
