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
import br.com.imarket.imarket.rest.RestGenerator;
import br.com.imarket.imarket.view.IMarketTextView;
import br.com.imarket.imarket.view.LoginEditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.refactor.library.SmoothCheckBox;

import static android.util.Patterns.EMAIL_ADDRESS;
import static br.com.imarket.imarket.util.TextValidation.isBlank;
import static br.com.imarket.imarket.util.TextValidation.isNotEquals;

public class RegisterFragment extends Fragment {

    private static final String TAG = "RegisterTag";

    @BindView(R.id.et_register_name)
    LoginEditText etRegisterName;

    @BindView(R.id.et_register_email)
    LoginEditText etRegisterEmail;

    @BindView(R.id.et_register_password)
    LoginEditText etRegisterPassword;
    @BindView(R.id.et_register_confirm_password)
    LoginEditText etRegisterConfirmPassword;
    @BindView(R.id.tv_newsletter)
    IMarketTextView tvNewsLetter;
    @BindView(R.id.cb_newsletter)
    SmoothCheckBox cbNewsletter;

    @BindView(R.id.tv_terms)
    IMarketTextView tvTerms;
    @BindView(R.id.cb_terms)
    SmoothCheckBox cbTerms;
    private View rootView;

    private final DrawerInteraction drawerInteraction;
    private LoginClient loginClient;
    private ProgressDialog registerDialog;
    private LoginService loginService;

    public RegisterFragment(DrawerInteraction drawerInteraction) {
        this.drawerInteraction = drawerInteraction;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.register_fragment, container, false);
        loginService = new LoginService();
        ButterKnife.bind(this, rootView);

        cbNewsletter.setChecked(true);
        cbTerms.setChecked(true);

        tvTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbTerms.setChecked(!cbTerms.isChecked(), true);
            }
        });

        tvNewsLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbNewsletter.setChecked(!cbNewsletter.isChecked(), true);
            }
        });

        loginClient = RestGenerator.createService(LoginClient.class);

        return rootView;
    }

    @OnClick(R.id.bt_confirm_register)
    public void confirmRegister() {
        String name = etRegisterName.getText().toString();
        String email = etRegisterEmail.getText().toString();
        String password = etRegisterPassword.getText().toString();
        String confirmPassword = etRegisterConfirmPassword.getText().toString();

        if (isBlank(name, email, password, confirmPassword)) {
            showMessage(R.string.invald_inform_all_fields);
            return;
        }
        if (isNotEquals(password, confirmPassword)) {
            showMessage(R.string.invalid_passwords_not_match);
            return;
        }
        if (password.length() < 6) {
            showMessage(R.string.invalid_password_length);
            return;
        }
        if (!EMAIL_ADDRESS.matcher(email).matches()) {
            showMessage(R.string.invalid_email);
            return;
        }
        registerDialog = ProgressDialog.show(getContext(), "", getString(R.string.loading), true);

        BuyerRegisterDTO dto = new BuyerRegisterDTO(name, email, password);
        loginService.register(dto, new LoginCallback() {

            @Override
            public void success(BuyerLogin buyerLogin) {
                drawerInteraction.changeFragment(new HomeFragment(), NavigationItem.HOME.getName());
                registerDialog.dismiss();
            }

            @Override
            public void invalidInfo() {
                registerDialog.cancel();
                IMarketSnackBar.show(rootView, getString(R.string.login_invalid_credentials), Snackbar.LENGTH_SHORT);
            }

            @Override
            public void error() {
                registerDialog.cancel();
                IMarketSnackBar.show(rootView, getString(R.string.register_error), Snackbar.LENGTH_SHORT);
            }
        });
    }

    private void showMessage(int messageId) {
        IMarketSnackBar.show(rootView, getString(messageId), Snackbar.LENGTH_SHORT);
    }
}
