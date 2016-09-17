package br.com.imarket.imarket;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import br.com.imarket.imarket.font.Font;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity{

    @BindView(R.id.tv_welcome)
    TextView tvWelcome;

    @BindView(R.id.tv_searching)
    TextView tvSearching;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchActivity);
        ButterKnife.bind(this);

        tvWelcome.setTypeface(Font.amatic(this));
        tvSearching.setTypeface(Font.amatic(this));
    }
}
