package com.example.itubeapp.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.itubeapp.R;
import com.example.itubeapp.data.roomEntity.User;
import com.example.itubeapp.databinding.FragmentAuthoraziationBinding;
import com.example.itubeapp.ui.viewModels.AuthorizationFragmentViewModel;

public class AuthoraziationFragment extends Fragment implements AuthorizationFragmentViewModel.LoginListener {
    private AuthorizationFragmentViewModel viewModel;
    private FragmentAuthoraziationBinding bindning;
    NavController navController;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AuthorizationFragmentViewModel.class);
        viewModel.initaiteDatabae(getContext(),this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bindning = FragmentAuthoraziationBinding.inflate(inflater);
        bindning.setLifecycleOwner(this);
        setupOnClickListeners();
        navController = NavHostFragment.findNavController(this);
        return bindning.getRoot();
    }

    private void setupOnClickListeners() {
        bindning.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = bindning.edTextUserNameLogin.getText().toString();
                String password = bindning.edTextEPasswordLogin.getText().toString();
                viewModel.login(userName,password);
            }
        });
        bindning.ResisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_authoraziationFragment_to_registerFragment);
            }
        });
    }

    @Override
    public void onLoginSuccess(User user) {
        Bundle bundle = new Bundle();
        bundle.putInt("Id", user.getId());
        navController.navigate(R.id.action_authoraziationFragment_to_homeFragment, bundle);
    }

    @Override
    public void onLoginError(String errorMessage) {
        Toast.makeText(requireContext(),errorMessage,Toast.LENGTH_SHORT).show();
    }
}