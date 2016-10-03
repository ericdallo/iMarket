package br.com.imarket.imarket;

import com.stephentuso.welcome.WelcomeScreenBuilder;
import com.stephentuso.welcome.ui.WelcomeActivity;
import com.stephentuso.welcome.util.WelcomeScreenConfiguration;

public class WelcomeScreenActivity extends WelcomeActivity{

    @Override
    protected WelcomeScreenConfiguration configuration() {
        return new WelcomeScreenBuilder(this)
                .theme(R.style.WelcomeScreenTheme)
                .defaultTitleTypefacePath("fonts/museo-sans-bold.ttf")
                .defaultHeaderTypefacePath("fonts/museo-sans-bold.ttf")
                .defaultBackgroundColor(R.color.white)
                .titlePage(R.drawable.cartwelcome, "Bem-vindo", R.color.colorPrimaryDark)
                .basicPage(R.drawable.cartwelcome, "iMarket", "Com o iMarket, é possivel comprar sem sair de casa", R.color.orange)
                .basicPage(R.drawable.cartwelcome, "Começando por...", "Basta escolher o mercado mais perto de você", R.color.yellow)
                .swipeToDismiss(true)
                .exitAnimation(android.R.anim.fade_out)
                .build();
    }
}
