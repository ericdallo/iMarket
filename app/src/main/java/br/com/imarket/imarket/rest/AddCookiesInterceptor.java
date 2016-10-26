package br.com.imarket.imarket.rest;

import android.util.Log;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import br.com.imarket.imarket.util.Preferences;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static br.com.imarket.imarket.util.Preferences.COOKIES;

class AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        Set<String> preferences = Preferences.getStringSet(COOKIES, new HashSet<String>());

        String finalCookie = "";

        for (String cookie : preferences) {
            if (cookie.contains("authorization_cookie")) {
                finalCookie += cookie + "; ";
            }
        }

        builder.addHeader("Cookie", finalCookie);

        Log.v("OkHttp", "Adding Header: " + finalCookie);
        return chain.proceed(builder.build());
    }
}
