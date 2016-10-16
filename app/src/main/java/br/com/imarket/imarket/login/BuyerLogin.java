package br.com.imarket.imarket.login;

import com.google.gson.annotations.SerializedName;

public class BuyerLogin {

    @SerializedName("name")
    private final String name;

    @SerializedName("email")
    private final String email;

    public BuyerLogin(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
