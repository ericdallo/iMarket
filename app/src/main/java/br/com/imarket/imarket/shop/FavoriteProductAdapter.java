package br.com.imarket.imarket.shop;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import br.com.imarket.imarket.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteProductAdapter extends RecyclerView.Adapter<FavoriteProductAdapter.FavoriteProductHolder> {

    private List<Product> products = Collections.emptyList();
    private Context context;

    public FavoriteProductAdapter(Context context) {
        this.context = context;
    }

    @Override
    public FavoriteProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false);

        return new FavoriteProductHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteProductHolder holder, int position) {
        TextView tvName = holder.tvName;
        TextView tvDescription = holder.tvDescription;
        ImageView ivProduct = holder.ivProduct;
        ImageView ivShelf = holder.ivShelf;
        TextView tvPrice = holder.tvPrice;

        int shelfDrawableId;

        if (position % 2 == 0) {
            shelfDrawableId = R.drawable.shelf_left;
        } else {
            shelfDrawableId = R.drawable.shelf_right;
        }

        if (position == products.size() - 1) {
            shelfDrawableId = R.drawable.shelf;
        }

        ivShelf.setImageDrawable(context.getResources().getDrawable(shelfDrawableId));
        tvName.setText(products.get(position).getName());
        tvDescription.setText(products.get(position).getDescription());
        tvPrice.setText(products.get(position).getPrice() + "");
        //ivProduct.setImageResource(products.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    static class FavoriteProductHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_shop_name) TextView tvName;
        @BindView(R.id.tv_shop_address)TextView tvDescription;
        @BindView(R.id.tv_price)TextView tvPrice;
        @BindView(R.id.iv_shop) ImageView ivProduct;
        @BindView(R.id.iv_products_shelf) ImageView ivShelf;

        FavoriteProductHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}