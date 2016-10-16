package br.com.imarket.imarket.login;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginClient {

    @FormUrlEncoded
    @POST("login")
    Call<Void> login(
            @Field("username") String email,
            @Field("password") String password
    );

    @GET("logged")
    Call<BuyerLogin> logged();

    @POST("register")
    Call<Void> register(@Body BuyerRegisterDTO buyerRegisterDTO);
}
