package br.com.imarket.imarket.shop;

import android.os.AsyncTask;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ShopListService extends AsyncTask<Void, Void, List<Product>> {

    private ShopCallback callback;

    public ShopListService(ShopCallback shopCallback) {
        this.callback = shopCallback;
    }

    @Override
    protected List<Product> doInBackground(Void... voids) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Arrays.asList(new Product("Kawahara", "Av. Dr. PereiraVergueiro, 556", BigDecimal.ONE),
                            new Product("Sonda", "Av. blabla de Souza com um nome muito grande", BigDecimal.TEN),
                            new Product("OutroMercado", "Av. Teste dos testes testados", BigDecimal.ONE),
                            new Product("MelhorMercadoDeSaoPaulo", "av 123 de Oliveira 5", BigDecimal.ZERO));
    }

    @Override
    protected void onPostExecute(List<Product> products) {
        callback.success(products);
    }
}
