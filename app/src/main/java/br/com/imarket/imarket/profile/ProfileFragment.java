package br.com.imarket.imarket.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.imarket.imarket.DrawerInteraction;

public class ProfileFragment extends Fragment {

    private DrawerInteraction drawerInteraction;

    public ProfileFragment(DrawerInteraction drawerInteraction) {
        this.drawerInteraction = drawerInteraction;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        drawerInteraction.logout();
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
