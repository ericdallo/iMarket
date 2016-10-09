package br.com.imarket.imarket.home;

import android.support.v4.app.Fragment;

import br.com.imarket.imarket.HomeFragment;
import br.com.imarket.imarket.R;

enum NavigationItem {

    CART(R.drawable.navigation_login_item, "Carrinho", "Gerencie sua compra") {
        @Override
        public Fragment getFragment() {
            return new HomeFragment();
        }
    },
    PAYMENT(R.drawable.navigation_login_item, "Caixa", "Maneiras de pagar") {
        @Override
        public Fragment getFragment() {
            return new HomeFragment();
        }
    },
    FAVORITED(R.drawable.navigation_login_item, "Prateleiras", "Seus produtos favoritos") {
        @Override
        public Fragment getFragment() {
            return new HomeFragment();
        }
    },
    OFFERS(R.drawable.navigation_login_item, "Ofertas", "Os melhores preços") {
        @Override
        public Fragment getFragment() {
            return new HomeFragment();
        }
    },
    ;

    private final int imagePath;
    private final String name;
    private final String description;

    NavigationItem(int imagePath, String name, String description) {
        this.imagePath = imagePath;
        this.name = name;
        this.description = description;
    }

    public int getImagePath() {
        return imagePath;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract Fragment getFragment();

}
