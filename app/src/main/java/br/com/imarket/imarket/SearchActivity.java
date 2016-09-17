package br.com.imarket.imarket;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import br.com.imarket.imarket.font.Font;
import br.com.imarket.imarket.shop.Shop;
import br.com.imarket.imarket.shop.ShopAdapter;
import br.com.imarket.imarket.shop.ShopCallback;
import br.com.imarket.imarket.shop.ShopListService;
import br.com.imarket.imarket.util.IMarketUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.tv_welcome)
    TextView tvWelcome;
    @BindView(R.id.tv_searching)
    TextView tvSearching;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    @BindView(R.id.rv_shops)
    RecyclerView rvShops;

    private ShopAdapter shopAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_shops);
        ButterKnife.bind(this);
        IMarketUtils.animateHeader(this);

        tvWelcome.setTypeface(Font.amatic(this));
        tvSearching.setTypeface(Font.amatic(this));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvShops.setLayoutManager(layoutManager);
        rvShops.setHasFixedSize(true);
        rvShops.setItemAnimator(new DefaultItemAnimator());
        rvShops.setVisibility(View.INVISIBLE);
        shopAdapter = new ShopAdapter();
        rvShops.setAdapter(shopAdapter);

        new ShopListService(new ShopCallback() {
            @Override
            public void success(List<Shop> shops) {
                tvSearching.setText(getString(R.string.available_shops));
                pbLoading.setVisibility(View.INVISIBLE);
                rvShops.setVisibility(View.VISIBLE);
                shopAdapter.setShops(shops);
                shopAdapter.notifyDataSetChanged();
            }
        }).execute();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
