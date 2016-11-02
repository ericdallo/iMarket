package br.com.imarket.imarket;

import android.support.v4.app.Fragment;

public interface DrawerInteraction {

    void changeFragment(Fragment fragment, String title);

    void logout();

    void setBackable(BackAction backAction);
}
