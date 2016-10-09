package br.com.imarket.imarket.home;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.imarket.imarket.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.NavigationHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private NavigationItem[] items = NavigationItem.values();
    private Context context;

    public NavigationAdapter(Context context) {
        this.context = context;
    }

    @Override
    public NavigationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = TYPE_ITEM;
        if (viewType == TYPE_ITEM) {
            layout = R.layout.navigation_item;
        } else if (viewType == TYPE_HEADER) {
            layout = R.layout.navigation_header;
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new NavigationHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(NavigationHolder holder, int position) {
        if (holder.type == TYPE_HEADER) {

        } else {
            TextView tvNavigationItem = holder.tvNavigationItem;
            ImageView ivNavigationItem = holder.ivNavigationItem;

            tvNavigationItem.setText(items[position - 1].getName());
            ivNavigationItem.setImageDrawable(context.getResources().getDrawable(items[position - 1].getImagePath()));
        }
    }

    @Override
    public int getItemCount() {
        return items.length + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    class NavigationHolder extends RecyclerView.ViewHolder {

        private int type;

        @Nullable @BindView(R.id.tv_navigation_item) TextView tvNavigationItem;
        @Nullable @BindView(R.id.iv_navigation_item) ImageView ivNavigationItem;

        NavigationHolder (View view, int viewType) {
            super(view);
            if (viewType == TYPE_ITEM) {
                ButterKnife.bind(this, view);
            }

            type = viewType;
        }
    }
}
