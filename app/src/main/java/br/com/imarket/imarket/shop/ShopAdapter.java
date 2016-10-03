package br.com.imarket.imarket.shop;

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

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopHolder> {

    private List<Shop> shops = Collections.emptyList();

    @Override
    public ShopHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_card, parent, false);

        return new ShopHolder(view);
    }

    @Override
    public void onBindViewHolder(ShopHolder holder, int position) {
        TextView tvName = holder.tvName;
        TextView tvAddress = holder.tvAddress;
        ImageView ivShop = holder.ivShop;

        tvName.setText(shops.get(position).getName());
        tvAddress.setText(shops.get(position).getAddress());
        //ivShop.setImageResource(shops.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return shops.size();
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }

    static class ShopHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_shop_name) TextView tvName;
        @BindView(R.id.tv_shop_address)TextView tvAddress;
        @BindView(R.id.iv_shop) ImageView ivShop;

        ShopHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}