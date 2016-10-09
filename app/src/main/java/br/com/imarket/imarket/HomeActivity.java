package br.com.imarket.imarket;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.math.BigDecimal;

import br.com.imarket.imarket.home.HomeBanner;
import br.com.imarket.imarket.home.NavigationAdapter;
import br.com.imarket.imarket.shop.Product;
import br.com.imarket.imarket.shop.FavoriteProductAdapter;
import br.com.imarket.imarket.util.IMarketUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

import static java.util.Arrays.asList;

public class HomeActivity extends AppCompatActivity {

    private static final int COLUMNS = 2;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lt_drawer)
    DrawerLayout ltDrawer;
    @BindView(R.id.rv_left_drawer)
    RecyclerView rvLeftDrawer;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;
    @BindView(R.id.lt_carousel)
    CarouselView carousel;
    @BindView(R.id.rv_favorite_products)
    RecyclerView rvFavoritesProducts;
    @BindView(R.id.rv_header)
    RecyclerViewHeader rvHeader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        ButterKnife.bind(this);
        IMarketUtils.animateHeader(this);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle actionBarToggle = new ActionBarDrawerToggle(this, ltDrawer, toolbar, R.string.open, R.string.close);
        ltDrawer.addDrawerListener(actionBarToggle);
        actionBarToggle.syncState();

        NavigationAdapter navigationAdapter = new NavigationAdapter(this);
        rvLeftDrawer.setHasFixedSize(true);
        rvLeftDrawer.setLayoutManager(new LinearLayoutManager(this));
        rvLeftDrawer.setAdapter(navigationAdapter);

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

        carousel.setPageCount(HomeBanner.size());
        ImageListener imageListener = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(HomeBanner.getImage(position));
            }
        };
        carousel.setImageListener(imageListener);

        rvFavoritesProducts.setHasFixedSize(true);
        rvFavoritesProducts.setLayoutManager(new GridLayoutManager(this, COLUMNS));
        FavoriteProductAdapter favoriteProductAdapter = new FavoriteProductAdapter(this);
        favoriteProductAdapter.setProducts(asList(new Product("Algum Shop", "Algum Endereço", BigDecimal.valueOf(2.99d)), new Product("Algum Shop", "Algum Endereçoasda", BigDecimal.valueOf(100.20d)), new Product("Algum Shopsss", "bla", BigDecimal.valueOf(2.0d))));
        rvFavoritesProducts.setAdapter(favoriteProductAdapter);

        rvHeader.attachTo(rvFavoritesProducts);
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
