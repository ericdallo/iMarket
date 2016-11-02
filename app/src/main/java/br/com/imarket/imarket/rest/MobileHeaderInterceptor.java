package br.com.imarket.imarket.rest;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class MobileHeaderInterceptor implements Interceptor {
    private static final String MOBILE_HEADER = "is_mobile";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        builder.addHeader(MOBILE_HEADER, "true");

        return chain.proceed(builder.build());
    }
}
