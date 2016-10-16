package br.com.imarket.imarket.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.imarket.imarket.DrawerInteraction;
import br.com.imarket.imarket.HomeFragment;
import br.com.imarket.imarket.R;
import br.com.imarket.imarket.home.NavigationItem;
import br.com.imarket.imarket.view.LoginEditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.text.TextUtils.isEmpty;

public class LoginFragment extends Fragment  {

    @BindView(R.id.et_login_email)
    LoginEditText etLoginEmail;
    @BindView(R.id.et_login_password)
    LoginEditText etLoginPassword;

    private View rootView;
    private DrawerInteraction drawerInteraction;
    private LoginService loginService;

    public LoginFragment(DrawerInteraction drawerInteraction) {
        this.drawerInteraction = drawerInteraction;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.login_fragment, container, false);
        ButterKnife.bind(this, rootView);
        loginService = new LoginService();

        return rootView;
    }

    @OnClick(R.id.bt_login)
    public void login() {
        String email = etLoginEmail.getText().toString();
        String password = etLoginPassword.getText().toString();

        if (isEmpty(email) || isEmpty(password)) {
            IMarketSnackBar.show(rootView, getString(R.string.inform_credentials), Snackbar.LENGTH_SHORT);
            return;
        }
        final ProgressDialog loginDialog = ProgressDialog.show(getContext(), "", getString(R.string.loading), true);

        loginService.login(email, password, new LoginCallback() {
            @Override
            public void success(BuyerLogin buyerLogin) {
                drawerInteraction.changeFragment(new HomeFragment(), NavigationItem.HOME.getName());
                loginDialog.dismiss();
            }

            @Override
            public void invalidInfo() {
                loginDialog.cancel();
                IMarketSnackBar.show(rootView, getString(R.string.login_invalid_credentials), Snackbar.LENGTH_SHORT);
            }

            @Override
            public void error() {
                loginDialog.cancel();
                IMarketSnackBar.show(rootView, getString(R.string.login_error), Snackbar.LENGTH_SHORT);
            }
        });
    }

    @OnClick(R.id.bt_register)
    public void register() {
        drawerInteraction.changeFragment(new RegisterFragment(drawerInteraction), getResources().getString(R.string.register_title));
    }
}
