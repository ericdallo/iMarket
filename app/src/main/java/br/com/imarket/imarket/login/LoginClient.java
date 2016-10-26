package br.com.imarket.imarket.login;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

import static br.com.imarket.imarket.util.IMarketConstants.LOGIN_INFO_URL;
import static br.com.imarket.imarket.util.IMarketConstants.LOGIN_RESOURCE_URL;
import static br.com.imarket.imarket.util.IMarketConstants.REGISTER_URL;

public interface LoginClient {

    @FormUrlEncoded
    @POST(LOGIN_RESOURCE_URL)
    Call<BuyerLogin> login(
            @Field("username") String email,
            @Field("password") String password
    );

    @DELETE(LOGIN_RESOURCE_URL)
    Call<Void> logout();

    @POST(REGISTER_URL)
    Call<Void> register(@Body BuyerRegisterDTO buyerRegisterDTO);
}
