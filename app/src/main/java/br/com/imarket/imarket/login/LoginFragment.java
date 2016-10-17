package br.com.imarket.imarket.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.imarket.imarket.DrawerInteraction;
import br.com.imarket.imarket.HomeFragment;
import br.com.imarket.imarket.R;
import br.com.imarket.imarket.home.NavigationItem;
import br.com.imarket.imarket.view.FacebookButton;
import br.com.imarket.imarket.view.LoginEditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.text.TextUtils.isEmpty;
import static com.facebook.FacebookSdk.getApplicationContext;

public class LoginFragment extends Fragment  {

    @BindView(R.id.et_login_email)
    LoginEditText etLoginEmail;
    @BindView(R.id.et_login_password)
    LoginEditText etLoginPassword;
    @BindView(R.id.bt_facebook_login)
    FacebookButton btFacebookLogin;

    private View rootView;
    private DrawerInteraction drawerInteraction;
    private LoginService loginService;
    private CallbackManager callbackManager;

    public LoginFragment(DrawerInteraction drawerInteraction) {
        this.drawerInteraction = drawerInteraction;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.login_fragment, container, false);
        ButterKnife.bind(this, rootView);
        loginService = new LoginService();

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        btFacebookLogin.setReadPermissions("email");
        btFacebookLogin.setFragment(this);

        btFacebookLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                final ProgressDialog loginDialog = ProgressDialog.show(getContext(), "", getString(R.string.loading), true);
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            String facebookId = object.getString("id");
                            String email = object.getString("email");
                            String name = object.getString("name");
                            BuyerRegisterDTO registerDTO = new BuyerRegisterDTO(name, email, facebookId, LoginOrigin.FACEBOOK);
                            loginService.register(registerDTO, new LoginCallback() {
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

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "name,email");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {
                IMarketSnackBar.show(rootView, getString(R.string.login_error), Snackbar.LENGTH_SHORT);
            }

            @Override
            public void onError(FacebookException error) {
                IMarketSnackBar.show(rootView, getString(R.string.login_error), Snackbar.LENGTH_SHORT);
            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.bt_login)
    public void login() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

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
