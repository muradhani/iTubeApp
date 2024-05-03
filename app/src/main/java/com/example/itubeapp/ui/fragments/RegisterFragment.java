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
import com.example.itubeapp.databinding.FragmentRegisterBinding;
import com.example.itubeapp.ui.viewModels.RegisterFragmentViewModel;

public class RegisterFragment extends Fragment implements RegisterFragmentViewModel.RegisterListener {

    public FragmentRegisterBinding bindning ;
    private RegisterFragmentViewModel viewModel;
    NavController navController;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(RegisterFragmentViewModel.class);
        viewModel.initaiteDatabae(getContext(),this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bindning = FragmentRegisterBinding.inflate(inflater);
        bindning.setLifecycleOwner(this);
        navController = NavHostFragment.findNavController(this);
        onclickListener();
        return bindning.getRoot();
    }

    private void onclickListener() {
        bindning.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = bindning.fullNameEd.getText().toString();
                String userName = bindning.UserNameBtn.getText().toString();
                String password = bindning.passwordEd.getText().toString();
                viewModel.registerUser(fullName,userName,password);
            }
        });
    }

    @Override
    public void onRegistrationComplete() {
        Toast.makeText(requireContext(),"register successfully",Toast.LENGTH_SHORT).show();
        navController.popBackStack();
    }
}