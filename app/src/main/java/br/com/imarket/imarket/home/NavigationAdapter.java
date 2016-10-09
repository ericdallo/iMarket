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
import de.hdodenhof.circleimageview.CircleImageView;

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.NavigationHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int HEADER_POSITION = 1;

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
            TextView tvNavigationItem = holder.tvTitleNavigationItem;
            TextView tvDescriptionNavigationItem = holder.tvDescriptionNavigationItem;
            ImageView ivNavigationItem = holder.cvNavigationItem;

            tvNavigationItem.setText(items[position - HEADER_POSITION].getName());
            tvDescriptionNavigationItem.setText(items[position - HEADER_POSITION].getDescription());
            ivNavigationItem.setImageDrawable(context.getResources().getDrawable(items[position - HEADER_POSITION].getImagePath()));
        }
    }

    @Override
    public int getItemCount() {
        return items.length + HEADER_POSITION;
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

        @Nullable @BindView(R.id.tv_title_navigation_item) TextView tvTitleNavigationItem;
        @Nullable @BindView(R.id.tv_description_navigation_item) TextView tvDescriptionNavigationItem;
        @Nullable @BindView(R.id.cv_navigation_item) CircleImageView cvNavigationItem;

        NavigationHolder (View view, int viewType) {
            super(view);
            if (viewType == TYPE_ITEM) {
                ButterKnife.bind(this, view);
            }

            type = viewType;
        }
    }
}
