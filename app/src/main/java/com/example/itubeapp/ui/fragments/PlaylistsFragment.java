// PlaylistsFragment.java
package com.example.itubeapp.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itubeapp.databinding.FragmentPlaylistsBinding;
import com.example.itubeapp.data.roomEntity.Playlist;
import com.example.itubeapp.ui.adapters.PlayListRvAdapter;
import com.example.itubeapp.ui.viewModels.PlaylistCallback;
import com.example.itubeapp.ui.viewModels.PlaylistsFragmentViewModel;

import java.util.Collections;
import java.util.List;

public class PlaylistsFragment extends Fragment implements PlaylistCallback {

    private FragmentPlaylistsBinding binding;
    private PlayListRvAdapter adapter;
    private PlaylistsFragmentViewModel viewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPlaylistsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        viewModel = new ViewModelProvider(this).get(PlaylistsFragmentViewModel.class);
        viewModel.initaiteDatabae(getContext());
        setupRecyclerView();
        Integer id = getArguments().getInt("id");
        // Observe the playlists LiveData and update the RecyclerView adapter
        viewModel.getPlaylistsByUserId(id,this);
        return binding.getRoot();
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = binding.playlistRv;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new PlayListRvAdapter(Collections.emptyList());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onPlaylistsLoaded(List<Playlist> playlists) {
        adapter.setData(playlists);
    }

    @Override
    public void onError(String errorMessage) {

    }
}
