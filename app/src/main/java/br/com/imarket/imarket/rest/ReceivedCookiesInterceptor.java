package br.com.imarket.imarket.rest;

import java.io.IOException;
import java.util.HashSet;

import br.com.imarket.imarket.util.Preferences;
import okhttp3.Interceptor;
import okhttp3.Response;

import static br.com.imarket.imarket.util.Preferences.COOKIES;

class ReceivedCookiesInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }

            Preferences.putStringSet(COOKIES, cookies);
        }

        return originalResponse;
    }
}