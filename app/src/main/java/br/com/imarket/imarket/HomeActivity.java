package br.com.imarket.imarket;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.Arrays;

import br.com.imarket.imarket.font.Font;
import br.com.imarket.imarket.shop.ShopAdapter;
import br.com.imarket.imarket.util.IMarketUtils;
import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;

import static java.util.Arrays.asList;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView tvToolbar;
    @BindView(R.id.lt_drawer)
    DrawerLayout ltDrawer;
    @BindView(R.id.rv_left_drawer)
    RecyclerView rvLeftDrawer;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        ButterKnife.bind(this);
        IMarketUtils.animateHeader(this);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        tvToolbar.setTypeface(Font.amatic(this));
        tvToolbar.setShadowLayer(1, 0, 0, Color.BLACK);

        ActionBarDrawerToggle actionBarToggle = new ActionBarDrawerToggle(this, ltDrawer, toolbar, R.string.open, R.string.close);
        ltDrawer.addDrawerListener(actionBarToggle);
        actionBarToggle.syncState();

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            moveTaskToBack(true);
        }
    }
}
