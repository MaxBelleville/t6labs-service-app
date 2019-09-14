package com.t6labs.locals.Login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.t6labs.locals.MainActivity;
import com.t6labs.locals.R;

import java.util.Arrays;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LoginFragment extends Fragment {

    private static final String TAG = LoginFragment.class.getName();
    private static final String TYPE_GOOGLE = "goolge";

    @BindView(R.id.google_sign_in)
    SignInButton signInButton;

    @BindView(R.id.fb_sign_in)
    LoginButton loginButton;

    private GoogleSignInClient signInClient;
    private Unbinder unbinder;
    private CallbackManager callbackManager;
    private LoginViewModel loginViewModel;

    @NonNull
    private Observer<Boolean> loginStateObserver = this::onLogInStateChange;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //TODO refactor
        Objects.requireNonNull((MainActivity) getActivity()).setActionBarTitle("Login", false);
        View view = inflater.inflate(R.layout.content_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initGoogleLogin();
        initFaceBookLogin();
        loginViewModel.getIsLoggedIn().observe(this, loginStateObserver);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        loginViewModel.getIsLoggedIn().removeObserver(loginStateObserver);
    }

    private void initFaceBookLogin() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken == null || accessToken.isExpired()) {
            callbackManager = CallbackManager.Factory.create();
            loginButton.setPermissions(Arrays.asList("email"));
            loginButton.setFragment(this);
            loginButton.registerCallback(callbackManager, loginViewModel.getFacebookCallback());
        }
    }

    private void initGoogleLogin() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        signInClient = GoogleSignIn.getClient(requireActivity(), gso);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(requireActivity());

        if (account != null) {
            loginViewModel.setLoggedIn(Boolean.TRUE);
        }

        signInButton.setOnClickListener(v -> {
            Intent signInIntent = signInClient.getSignInIntent();
            startActivityForResult(signInIntent, 1);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            if (account != null) {
                loginViewModel.login(requireContext(), TYPE_GOOGLE, account.getIdToken());
            } else
                Log.d(TAG, "onActivityResult: no account");
        } catch (ApiException e) {
            Log.d(TAG, "sign-in fail: " + e.getMessage());
        }
    }

    private void onLogInStateChange(Boolean isLoggedIn) {
        if (isLoggedIn) {
            Navigation.findNavController(getView()).navigate(LoginFragmentDirections.actionLoginToAccount());
        }
    }
}