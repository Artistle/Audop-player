package com.example.artistle.play_music_service.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.artistle.play_music_service.Model.SongModel;
import com.example.artistle.play_music_service.Presenter.RecyclerSongAdapter;
import com.example.artistle.play_music_service.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<SongModel> listSong;
    private RecyclerView recyclerView;
    private RecyclerSongAdapter recyclerSongAdapter;

    public static final String Broadcast_PLAY_NEW_AUDIO = "com.example.artistle.play_music_service.Activity.PlayNewAudio";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        listSong = new ArrayList<SongModel>();
        loadAudio();
        recyclerSongAdapter = new RecyclerSongAdapter(listSong);
        recyclerView.setAdapter(recyclerSongAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    private void loadAudio() {
        ContentResolver contentResolver = getContentResolver();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";

        Cursor cursor = contentResolver.query(uri, null, selection, null, sortOrder);

        if (cursor != null && cursor.getCount() > 0) {
            listSong = new ArrayList<>();
            while (cursor.moveToNext()) {
                String data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));

                // Save to audioList
                listSong.add(new SongModel(data, title, album, artist));
            }
        }
        if (cursor != null)
            cursor.close();
    }
}

