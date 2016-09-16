package br.com.imarket.imarket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import br.com.imarket.imarket.font.Font;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imarket);

        TextView tvImarket = (TextView) findViewById(R.id.tv_imarket);
        tvImarket.setTypeface(Font.amatic(this));
    }
}
