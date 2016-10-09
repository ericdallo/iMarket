package br.com.imarket.imarket;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.math.BigDecimal;

import br.com.imarket.imarket.home.HomeBanner;
import br.com.imarket.imarket.shop.FavoriteProductAdapter;
import br.com.imarket.imarket.shop.Product;
import butterknife.BindView;
import butterknife.ButterKnife;

import static java.util.Arrays.asList;

public class HomeFragment extends Fragment {

    private static final int COLUMNS = 2;

    @BindView(R.id.lt_carousel)
    CarouselView carousel;
    @BindView(R.id.rv_favorite_products)
    RecyclerView rvFavoritesProducts;
    @BindView(R.id.rv_header)
    RecyclerViewHeader rvHeader;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        ButterKnife.bind(this, view);

        carousel.setPageCount(HomeBanner.size());
        ImageListener imageListener = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(HomeBanner.getImage(position));
            }
        };
        carousel.setImageListener(imageListener);

        rvFavoritesProducts.setHasFixedSize(true);
        rvFavoritesProducts.setLayoutManager(new GridLayoutManager(view.getContext(), COLUMNS));
        FavoriteProductAdapter favoriteProductAdapter = new FavoriteProductAdapter(view.getContext());
        favoriteProductAdapter.setProducts(asList(new Product("Algum Shop", "Algum Endereço", BigDecimal.valueOf(2.99d)), new Product("Algum Shop", "Algum Endereçoasda", BigDecimal.valueOf(100.20d)), new Product("Algum Shopsss", "bla", BigDecimal.valueOf(2.0d))));
        rvFavoritesProducts.setAdapter(favoriteProductAdapter);

        rvHeader.attachTo(rvFavoritesProducts);

        return view;
    }
}
