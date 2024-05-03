package com.example.itubeapp.ui.adapters;

import com.bumptech.glide.Glide;
import com.example.itubeapp.R;
import com.example.itubeapp.data.roomEntity.Playlist;
import com.example.itubeapp.databinding.PlaylistsRvItemBinding;
import com.example.itubeapp.ui.base.BaseRecyclerViewAdapter;

import java.util.List;

public class PlayListRvAdapter extends BaseRecyclerViewAdapter<PlaylistsRvItemBinding, Playlist> {

    public PlayListRvAdapter(List<Playlist> initialData) {
        super(initialData);
    }
    @Override
    public void bind(PlaylistsRvItemBinding binding, Playlist item) {
       binding.playlistTv.setText(item.getPlaylistLink());
    }

    @Override
    public int getLayoutId() {
        return R.layout.playlists_rv_item;
    }
}