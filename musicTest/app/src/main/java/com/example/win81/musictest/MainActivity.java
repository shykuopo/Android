package com.example.win81.musictest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.media.AudioManager;
import android.media.SoundPool;


public class MainActivity extends AppCompatActivity {

    private Button mb1,mb2;
    private SoundPool spool;
    private int sourceid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spool = new SoundPool(10, AudioManager.STREAM_MUSIC, 5);
        sourceid = spool.load(this, R.raw.bomb, 1);


    }

    public void start(View v){
        playSud(0);
    }

    public void pause(View v){
        spool.pause(sourceid);
    }

    public void playSud(int repeatTime) {
        AudioManager am = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);


        float audMaxVolumn = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        spool.play(sourceid, 1.0F, 1.0F, 1, repeatTime, 1);
    }
}
