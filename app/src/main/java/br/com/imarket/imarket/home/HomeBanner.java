package br.com.imarket.imarket.home;

import br.com.imarket.imarket.R;

public class HomeBanner {

    private static int[] images = {
            R.drawable.banner1,
            R.drawable.carousel_teste,
            R.drawable.banner1,
            R.drawable.carousel_teste
    };

    public static int size() {
        return images.length;
    }

    public static int getImage(int position) {
        return images[position];
    }
}
