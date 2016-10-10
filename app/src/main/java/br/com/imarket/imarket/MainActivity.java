package br.com.imarket.imarket;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import br.com.imarket.imarket.home.NavigationAdapter;
import br.com.imarket.imarket.util.IMarketUtils;
import br.com.imarket.imarket.view.AmaticTextView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements DrawerInteraction {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.dt_drawer)
    DrawerLayout dtDrawer;
    @BindView(R.id.rv_left_drawer)
    RecyclerView rvLeftDrawer;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;
    @BindView(R.id.toolbar_title)
    AmaticTextView toolbarTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        IMarketUtils.animateHeader(this);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle actionBarToggle = new ActionBarDrawerToggle(this, dtDrawer, toolbar, R.string.open, R.string.close);
        dtDrawer.addDrawerListener(actionBarToggle);
        actionBarToggle.syncState();

        NavigationAdapter navigationAdapter = new NavigationAdapter(this, this);
        rvLeftDrawer.setHasFixedSize(true);
        rvLeftDrawer.setLayoutManager(new LinearLayoutManager(this));
        rvLeftDrawer.setAdapter(navigationAdapter);
        rvLeftDrawer.setBackgroundColor(getResources().getColor(R.color.shelf_background));

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

        dtDrawer.closeDrawer(rvLeftDrawer);
        changeFragment(new HomeFragment(), getResources().getString(R.string.imarket));
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

    @Override
    public void changeFragment(Fragment fragment, String title) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.lt_main_content, fragment);
        transaction.commit();
        toolbarTitle.setText(title);
        dtDrawer.closeDrawer(rvLeftDrawer);
    }
}
