package br.com.imarket.imarket.login;

import com.google.gson.annotations.SerializedName;

public class BuyerRegisterDTO {

    @SerializedName("name")
    private final String name;

    @SerializedName("email")
    private final String email;

    @SerializedName("password")
    private final String password;

    public BuyerRegisterDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
