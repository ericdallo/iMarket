package br.com.imarket.imarket.home;

import br.com.imarket.imarket.R;

public enum NavigationItem {

    LOGIN(R.drawable.navigation_login_item, "Login"),
    ABOUT(R.drawable.navigation_about_item, "Sobre"),
    ;

    private int imagePath;
    private String name;

    NavigationItem(int imagePath, String name) {
        this.imagePath = imagePath;
        this.name = name;
    }

    public int getImagePath() {
        return imagePath;
    }

    public String getName() {
        return name;
    }
}
