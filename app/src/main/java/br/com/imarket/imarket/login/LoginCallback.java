package br.com.imarket.imarket.login;

public interface LoginCallback {

    void success(BuyerLogin buyerLogin);

    void invalidInfo();

    void invalidType();

    void error();
}
