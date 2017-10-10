package com.example.win81.audioandvideo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

public class Video extends AppCompatActivity implements MediaPlayer.OnCompletionListener{

    VideoView vdv;
    int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//為了隱藏系統狀態列
        getSupportActionBar().hide();//隱藏Activity標題列
        setContentView(R.layout.activity_video);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Intent it  = getIntent();
        Uri uri = Uri.parse(it.getStringExtra("uri"));
        if(savedInstanceState !=null)  //如果因為旋轉啟動Activity
            pos = savedInstanceState.getInt("pos",0);//取出旋轉前撥放位置

        vdv = (VideoView)findViewById(R.id.videoView2);
        MediaController mediaCtrl = new MediaController(this);

        vdv.setMediaController(mediaCtrl);
        vdv.setVideoURI(uri);
        vdv.setOnCompletionListener(this);
    }
    @Override
    protected void onResume(){
        super.onResume();
        vdv.seekTo(pos);
        vdv.start();
    }
    @Override
    protected void onPause(){
        super.onPause();
        pos = vdv.getCurrentPosition();
        vdv.stopPlayback();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("pos",pos);
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        finish();
    }
}
