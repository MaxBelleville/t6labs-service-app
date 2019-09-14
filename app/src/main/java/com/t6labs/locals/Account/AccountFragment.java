package com.t6labs.locals.Account;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.t6labs.locals.Login.LoginFragmentDirections;
import com.t6labs.locals.MainActivity;
import com.t6labs.locals.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class AccountFragment extends Fragment {

    @OnClick(R.id.create_listing)
    void createListing(View view) {
        Navigation.findNavController(getView()).navigate(AccountFragmentDirections.actionAccountToNewListing());
    }
    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_account, container, false);
        ButterKnife.bind(this, view);
        //TODO refactor
        Objects.requireNonNull((MainActivity) getActivity()).setActionBarTitle(getActivity().getString(R.string.title_account), true);
        return view;
    }

}
