package com.t6labs.locals.Login;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.t6labs.locals.MainActivity;
import com.t6labs.locals.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LoginFragment extends Fragment {

    @BindView(R.id.google_sign_in)
    SignInButton signInButton;

    private GoogleSignInClient signInClient;
    private Unbinder unbinder;

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
        initSignIn();
    }

    private void initSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        signInClient = GoogleSignIn.getClient(requireActivity(), gso);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(requireActivity());
        if(account!=null) {
            Navigation.findNavController(getParentFragment().getView()).navigate(LoginFragmentDirections.actionLoginToNewListing());
        }

        signInButton.setOnClickListener(v -> {
            Intent signInIntent = signInClient.getSignInIntent();
            startActivityForResult(signInIntent, 1);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        Navigation.findNavController(getView()).navigate(LoginFragmentDirections.actionLoginToNewListing());
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            if(account!=null)
                Navigation.findNavController(getView()).navigate(LoginFragmentDirections.actionLoginToNewListing());
            else
                Log.d("GOOGLESIGNIN","no account found");
        } catch (ApiException e) {
           Log.d("GOOGLESIGNIN",e.getMessage());
        }
    }
}