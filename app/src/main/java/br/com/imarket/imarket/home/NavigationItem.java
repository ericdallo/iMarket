package br.com.imarket.imarket.home;

import br.com.imarket.imarket.R;

enum NavigationItem {

    CART(R.drawable.navigation_login_item, "Carrinho", "Gerencie sua compra"),
    PAYMENT(R.drawable.navigation_login_item, "Caixa", "Maneiras de pagar"),
    FAVORITED(R.drawable.navigation_login_item, "Prateleiras", "Seus produtos favoritos"),
    OFFERS(R.drawable.navigation_login_item, "Ofertas", "Os melhores preços"),
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
}
