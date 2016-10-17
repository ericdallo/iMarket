package br.com.imarket.imarket.login;

import com.google.gson.annotations.SerializedName;

public class BuyerRegisterDTO {

    @SerializedName("name")
    private final String name;

    @SerializedName("email")
    private final String email;

    @SerializedName("password")
    private final String password;

    @SerializedName("login_origin")
    private final LoginOrigin loginOrigin;

    public BuyerRegisterDTO(String name, String email, String password, LoginOrigin loginOrigin) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.loginOrigin = loginOrigin;
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

    public LoginOrigin getLoginOrigin() {
        return loginOrigin;
    }
}
