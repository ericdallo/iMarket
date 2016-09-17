package br.com.imarket.imarket;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import br.com.imarket.imarket.font.Font;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static br.com.imarket.imarket.util.LocationUtil.isGPSEnabled;

public class LocationActivity extends AppCompatActivity {

    @BindView(R.id.tv_imarket)
    TextView tvImarket;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);
        ButterKnife.bind(this);

        tvImarket.setTypeface(Font.amatic(this));
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isGPSEnabled(this)) {
            startActivity(new Intent(this, SearchActivity.class));
            finish();
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
