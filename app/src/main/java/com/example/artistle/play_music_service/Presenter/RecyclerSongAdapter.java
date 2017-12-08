package com.example.artistle.play_music_service.Presenter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.artistle.play_music_service.Model.SongModel;
import com.example.artistle.play_music_service.R;

import java.util.ArrayList;

/**
 * Created by artistle on 06.12.17.
 */

public class RecyclerSongAdapter extends RecyclerView.Adapter<RecyclerSongAdapter.ViewHolder> {

    private ArrayList<SongModel> list;

    public RecyclerSongAdapter(ArrayList<SongModel> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SongModel songs = list.get(position);

        holder.artist.setText(songs.getAlbum());
        holder.title.setText(songs.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView artist;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.name_song);
            artist = (TextView)itemView.findViewById(R.id.artist);
        }
    }
}
