package br.com.imarket.imarket.login;

import android.util.Log;

import br.com.imarket.imarket.rest.RestGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginService {

    private static final String TAG = "LoginServiceTag";

    private final LoginClient loginClient;

    public LoginService() {
        this.loginClient = RestGenerator.createService(LoginClient.class);
    }

    public void login(String email, String password, final LoginCallback callback) {
        realizeLogin(email, password, callback);
    }

    public void register(final BuyerRegisterDTO dto, final LoginCallback callback) {
        loginClient.register(dto).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    realizeLogin(dto.getEmail(), dto.getPassword(), callback);
                } else {
                    callback.invalidInfo();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "Error on processing register", t);
                callback.error();
            }
        });
    }

    private void realizeLogin(final String email, final String password, final LoginCallback callback) {
        loginClient.login(email, password).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    requestBuyerInfo(callback);
                } else {
                    callback.invalidInfo();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "Error on processing login", t);
                callback.error();
            }
        });
    }

    private void requestBuyerInfo(final LoginCallback callback) {
        loginClient.logged().enqueue(new Callback<BuyerLogin>() {
            @Override
            public void onResponse(Call<BuyerLogin> call, Response<BuyerLogin> response) {
                if (response.isSuccessful()) {
                    LoggedBuyer.setBuyer(response.body());
                    callback.success(response.body());
                } else {
                    callback.invalidInfo();
                }
            }

            @Override
            public void onFailure(Call<BuyerLogin> call, Throwable t) {
                Log.d(TAG, "Error on processing login", t);
                callback.error();
            }
        });
    }
}
