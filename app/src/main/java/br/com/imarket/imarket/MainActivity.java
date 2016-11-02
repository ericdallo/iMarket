package br.com.imarket.imarket;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import br.com.imarket.imarket.home.NavigationAdapter;
import br.com.imarket.imarket.login.BuyerLogin;
import br.com.imarket.imarket.login.LoggedBuyer;
import br.com.imarket.imarket.login.LoginFragment;
import br.com.imarket.imarket.login.LoginService;
import br.com.imarket.imarket.login.LogoutCallback;
import br.com.imarket.imarket.profile.ProfileFragment;
import br.com.imarket.imarket.util.IMarketUtils;
import br.com.imarket.imarket.util.Preferences;
import br.com.imarket.imarket.view.AmaticTextView;
import br.com.imarket.imarket.view.IMarketTextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static br.com.imarket.imarket.login.LoggedBuyer.isLogged;

public class MainActivity extends AppCompatActivity implements DrawerInteraction {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.dl_drawer)
    DrawerLayout dlDrawer;
    @BindView(R.id.lt_navigation)
    LinearLayout ltNavigation;
    @BindView(R.id.rv_left_drawer)
    RecyclerView rvLeftDrawer;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;
    @BindView(R.id.toolbar_title)
    AmaticTextView toolbarTitle;
    @BindView(R.id.navigation_header_container)
    NavigationView navigationHeaderView;

    private IMarketTextView headerTitle;
    private IMarketTextView headerDescription;

    private LoginService loginService;

    private BackAction backAction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        IMarketUtils.animateHeader(this);
        Preferences.setActivity(this);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle actionBarToggle = new ActionBarDrawerToggle(this, dlDrawer, toolbar, R.string.open, R.string.close);
        dlDrawer.addDrawerListener(actionBarToggle);
        actionBarToggle.syncState();

        NavigationAdapter navigationAdapter = new NavigationAdapter(this);
        rvLeftDrawer.setHasFixedSize(true);
        rvLeftDrawer.setLayoutManager(new LinearLayoutManager(this));
        rvLeftDrawer.setAdapter(navigationAdapter);

        headerTitle = (IMarketTextView) navigationHeaderView.getHeaderView(0).findViewById(R.id.tv_header_title);
        headerDescription = (IMarketTextView) navigationHeaderView.getHeaderView(0).findViewById(R.id.tv_header_description);
        checkLogin();

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

        changeMainFragment(new HomeFragment(), getResources().getString(R.string.imarket));
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
        } else if (backAction != null) {
            backAction.execute();
            backAction = null;
        } else {
            moveTaskToBack(true);
        }
    }

    @Override
    public void setBackable(BackAction backAction) {
        this.backAction = backAction;
    }

    @Override
    public void changeFragment(Fragment fragment, String title) {
        changeMainFragment(fragment, title);
    }

    @Override
    public void logout() {
        final ProgressDialog logoutDialog = ProgressDialog.show(this, "", getString(R.string.logging_out), true);

        if (loginService == null) {
            loginService = new LoginService();
        }
        loginService.logout(new LogoutCallback() {
            @Override
            public void success() {
                LoginManager.getInstance().logOut();
                logoutDialog.dismiss();
                changeMainFragment(new HomeFragment(), getString(R.string.imarket));
            }

            @Override
            public void error() {
                logoutDialog.cancel();
            }
        });
    }

    private void changeMainFragment(Fragment fragment, String title) {
        checkLogin();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.lt_main_content, fragment);
        transaction.commit();
        toolbarTitle.setText(title);
        dlDrawer.closeDrawer(ltNavigation);
    }

    private void checkLogin() {
        if (isLogged()) {
            BuyerLogin buyer = LoggedBuyer.getBuyer();
            headerTitle.setText(buyer.getName());
            headerDescription.setText(getString(R.string.my_profile));
            final ProfileFragment profileFragment = new ProfileFragment(this);
            navigationHeaderView.getHeaderView(0).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeMainFragment(profileFragment , getString(R.string.my_profile));
                }
            });
        } else {
            headerTitle.setText(getString(R.string.welcome));
            headerDescription.setText(getString(R.string.login));
            final LoginFragment loginFragment = new LoginFragment(this);
            navigationHeaderView.getHeaderView(0).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeMainFragment(loginFragment, getString(R.string.login));
                }
            });
        }
    }
}
