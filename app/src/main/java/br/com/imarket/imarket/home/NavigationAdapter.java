package br.com.imarket.imarket.home;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.imarket.imarket.DrawerInteraction;
import br.com.imarket.imarket.MainActivity;
import br.com.imarket.imarket.R;
import br.com.imarket.imarket.view.IMarketTextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.NavigationHolder> {

    private NavigationItem[] items = NavigationItem.values();
    private Context context;
    private DrawerInteraction drawerInteraction;

    public NavigationAdapter(MainActivity mainActivity) {
        this.context = mainActivity;
        this.drawerInteraction = mainActivity;
    }

    @Override
    public NavigationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NavigationHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.navigation_item, parent, false));
    }

    @Override
    public void onBindViewHolder(NavigationHolder holder, final int position) {
        View view = holder.viewItem;

        final NavigationItem currentItem = items[position];

        TextView tvNavigationItem = holder.tvTitleNavigationItem;
        TextView tvDescriptionNavigationItem = holder.tvDescriptionNavigationItem;
        ImageView ivNavigationItem = holder.cvNavigationItem;

        tvNavigationItem.setText(currentItem.getName());
        tvDescriptionNavigationItem.setText(currentItem.getDescription());
        ivNavigationItem.setImageDrawable(context.getResources().getDrawable(currentItem.getImagePath()));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerInteraction.changeFragment(currentItem.getFragment(), currentItem.getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    class NavigationHolder extends RecyclerView.ViewHolder {

        @Nullable
        @BindView(R.id.lt_item)
        View viewItem;
        @Nullable
        @BindView(R.id.tv_title_navigation_item)
        TextView tvTitleNavigationItem;
        @Nullable
        @BindView(R.id.tv_description_navigation_item)
        TextView tvDescriptionNavigationItem;
        @Nullable
        @BindView(R.id.cv_navigation_item)
        CircleImageView cvNavigationItem;
        @Nullable
        @BindView(R.id.tv_header_title)
        IMarketTextView tvHeaderTitle;
        @Nullable
        @BindView(R.id.tv_header_description)
        IMarketTextView tvHeaderDescription;

        NavigationHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
