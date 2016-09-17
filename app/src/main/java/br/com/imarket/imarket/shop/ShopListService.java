package br.com.imarket.imarket.shop;

import android.os.AsyncTask;

import java.util.Arrays;
import java.util.List;

public class ShopListService extends AsyncTask<Void, Void, List<Shop>> {

    private ShopCallback callback;

    public ShopListService(ShopCallback shopCallback) {
        this.callback = shopCallback;
    }

    @Override
    protected List<Shop> doInBackground(Void... voids) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Arrays.asList(new Shop("Kawahara", "Av. Dr. PereiraVergueiro, 556"),
                            new Shop("Sonda", "Av. blabla de Souza com um nome muito grande"),
                            new Shop("OutroMercado", "Av. Teste dos testes testados"),
                            new Shop("MelhorMercadoDeSaoPaulo", "av 123 de Oliveira 5"));
    }

    @Override
    protected void onPostExecute(List<Shop> shops) {
        callback.success(shops);
    }
}
