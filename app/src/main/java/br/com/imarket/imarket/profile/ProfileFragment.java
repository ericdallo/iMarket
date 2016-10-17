package br.com.imarket.imarket.profile;

import android.support.v4.app.Fragment;

import br.com.imarket.imarket.DrawerInteraction;

public class ProfileFragment extends Fragment {

    private DrawerInteraction drawerInteraction;

    public ProfileFragment(DrawerInteraction drawerInteraction) {
        this.drawerInteraction = drawerInteraction;
    }
}
