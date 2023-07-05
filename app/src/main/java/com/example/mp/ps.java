package com.example.mp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class ps extends AppCompatActivity {

    TextView textView;
    ImageView play, next, previous;
    public ArrayList<File> songs;
    int position;
    MediaPlayer mediaPlayer;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();}

        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_ps);

            textView = findViewById(R.id.textView);
            play = findViewById(R.id.play);
            previous = findViewById(R.id.previous);
            next = findViewById(R.id.next);

            Intent intent = getIntent();
            Bundle bundle = ((Intent) intent).getExtras();
            songs = (ArrayList) bundle.getParcelableArrayList("songList");
            String textContent = intent.getStringExtra("currentSong");
            textView.setText(textContent);
            position = intent.getIntExtra("position", 0);
            Uri uri = Uri.parse(songs.get(position).toString());
            mediaPlayer = MediaPlayer.create(this, uri);
            mediaPlayer.start();

            play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mediaPlayer.isPlaying()){
                        play.setImageResource(R.drawable.play);
                        mediaPlayer.pause();
                    }
                    else{
                        play.setImageResource(R.drawable.pause);
                        mediaPlayer.start();
                    }

                }
            });

            previous.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    if(position!=0){
                        position = position - 1;
                    }
                    else{
                        position = songs.size() - 1;
                    }
                    Uri uri = Uri.parse(songs.get(position).toString());
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
                    mediaPlayer.start();
                    play.setImageResource(R.drawable.pause);
                    String textContent = songs.get(position).getName().toString();
                    textView.setText(textContent);
                }
            });


            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    if(position!=songs.size()-1){
                        position = position + 1;
                    }
                    else{
                        position = 0;
                    }
                    Uri uri = Uri.parse(songs.get(position).toString());
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
                    mediaPlayer.start();
                    play.setImageResource(R.drawable.pause);
                    String textContent = songs.get(position).getName().toString();
                    textView.setText(textContent);

                }
            });



        }
    }
