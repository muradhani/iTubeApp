package com.example.itubeapp.ui.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.itubeapp.data.database.AppDatabase;
import com.example.itubeapp.databinding.FragmentHomeBinding;
import com.example.itubeapp.R;
import com.example.itubeapp.databinding.FragmentRegisterBinding;
import com.example.itubeapp.ui.viewModels.HomeFragmentViewModel;
import com.example.itubeapp.ui.viewModels.RegisterFragmentViewModel;


public class HomeFragment extends Fragment implements HomeFragmentViewModel.AddPlaylistListener {
    public FragmentHomeBinding bindning ;
    private HomeFragmentViewModel viewModel;
    NavController navController;
    Integer UserId ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);
        viewModel.initaiteDatabae(getContext(),this);
        UserId = getArguments().getInt("id");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bindning = FragmentHomeBinding.inflate(inflater);
        bindning.setLifecycleOwner(this);
        navController = NavHostFragment.findNavController(this);
        onclickListener();
        return bindning.getRoot();
    }

    private void onclickListener() {
        bindning.playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String videoUrl = bindning.edVideoUrl.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("video", videoUrl);
                navController.navigate(R.id.action_homeFragment_to_videoWebViewFragment, bundle);
            }
        });
        bindning.addToPlayListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoUrl = bindning.edVideoUrl.getText().toString();
                viewModel.addPlaylistLink(UserId,videoUrl);
            }
        });
        bindning.myPlayListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_authoraziationFragment_to_registerFragment);
            }
        });
    }

    @Override
    public void onPlaylistAdded() {
        Toast.makeText(requireContext(),"Link added",Toast.LENGTH_SHORT).show();

    }
}