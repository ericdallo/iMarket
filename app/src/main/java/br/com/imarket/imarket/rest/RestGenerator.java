package br.com.imarket.imarket.rest;


import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestGenerator {

    private static final String API_BASE_URL = "http://192.168.0.20:8080";

    private static final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    private static ReceivedCookiesInterceptor receivedCookies = new ReceivedCookiesInterceptor();
    private static AddCookiesInterceptor addCookies = new AddCookiesInterceptor();

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <T> T createService(Class<T> serviceClass) {
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        httpClient.addInterceptor(logging);
        httpClient.addInterceptor(addCookies);
        httpClient.addInterceptor(receivedCookies);
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }
}
