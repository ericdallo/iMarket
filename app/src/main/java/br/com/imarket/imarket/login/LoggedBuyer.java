package br.com.imarket.imarket.login;

public class LoggedBuyer {

    private static BuyerLogin buyer;

    private static boolean logged;

    public static boolean isLogged() {
        return logged;
    }

    public static void setBuyer(BuyerLogin buyer) {
        LoggedBuyer.buyer = buyer;
        logged = true;
    }

    public static BuyerLogin getBuyer() {
        return buyer;
    }

    public static void logout() {
        buyer = null;
        logged = false;
    }
}
