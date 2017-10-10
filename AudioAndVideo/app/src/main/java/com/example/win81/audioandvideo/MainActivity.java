package com.example.win81.audioandvideo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
        MediaPlayer.OnPreparedListener,
        MediaPlayer.OnErrorListener,
        MediaPlayer.OnCompletionListener{


    Uri uri;
    TextView txvName, txvUri;
    boolean isVideo = false;
    Button btnPlay,btnStop;
    CheckBox ckbLoop;
    MediaPlayer mper;
    Toast tos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);//螢幕部旋轉
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//螢幕直立
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); //螢幕不休眠

        txvName = (TextView)findViewById(R.id.txvName);
        txvUri = (TextView)findViewById(R.id.txvUri);
        btnPlay = (Button)findViewById(R.id.btnPlay);
        btnStop = (Button)findViewById(R.id.btnStop);
        ckbLoop = (CheckBox)findViewById(R.id.ckbLoop);


        uri = Uri.parse("andriod.resource:://"+getPackageName()+"/"+R.raw.music_1);

        txvName.setText("music.mp3");
        txvUri.setText("程式內的樂曲是："+uri.toString());

        mper = new MediaPlayer();
        mper.setOnPreparedListener(this);
        mper.setOnErrorListener(this);
        mper.setOnCompletionListener(this);
        tos = Toast.makeText(this,"",Toast.LENGTH_SHORT);

        prepareMedia();

    }
    public void onPick(View v){
        Intent it = new Intent(Intent.ACTION_GET_CONTENT);

        if(v.getId() == R.id.btnPickAudio){
            it.setType("audio/*");
            startActivityForResult(it,100);
        }
        else{
            it.setType("video/*");
            startActivityForResult(it,101);
        }
    }

    public void onMpPlay(View v){
        if(isVideo){
            Intent it = new Intent(this,Video.class);
            it.putExtra("uri",uri.toString());
            startActivity(it);
            return;
        }

        if(mper.isPlaying()){
            mper.pause();
            btnPlay.setText("繼續");
        }
        else{
            mper.start();
            btnPlay.setText("暫停");
            btnStop.setEnabled(true);
        }
    }
    public void onMpStop(View v){
        mper.pause();
        mper.seekTo(0);
        btnPlay.setText("播放");
        btnStop.setEnabled(false);
    }
    public void onMpLoop(View v){
        if(ckbLoop.isChecked())
            mper.setLooping(true);
        else
            mper.setLooping(false);
    }
    public void onMpBackward(View v){
        if(!btnPlay.isEnabled()) return;
        int len = mper.getDuration();//讀取音樂長度
        int pos = mper.getCurrentPosition();//讀取目前撥放位置
        pos-=10000;
        if(pos <0) pos = 0;
        mper.seekTo(pos);

        tos.setText("倒退10秒:"+pos/1000+"/"+len/1000);
        tos.show();

    }
    public void onMpForward(View v){
        if(!btnPlay.isEnabled()) return;
        int len = mper.getDuration();//讀取音樂長度
        int pos = mper.getCurrentPosition();//讀取目前撥放位置
        pos+=10000;
        if(pos >len) pos = len;
        mper.seekTo(pos);

        tos.setText("前進10秒:"+pos/1000+"/"+len/1000);
        tos.show();

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(resultCode == Activity.RESULT_OK){
            isVideo = (requestCode ==101);

            uri = data.getData();
            txvName.setText((isVideo?"影片:":"歌曲:")+getFilename(uri));
            txvUri.setText("檔案URI："+uri.toString());
            tos.setText("onActivityResult");
            tos.show();
            prepareMedia();
        }
    }

    String getFilename(Uri uri){
        String fileName = null;
        String[] colName = {MediaStore.MediaColumns.DISPLAY_NAME};
        Cursor cursor = getContentResolver().query(uri,colName,null,null,null);
        cursor.moveToFirst();
        fileName = cursor.getString(0);
        cursor.close();
        return fileName;

    }

    void prepareMedia(){
        btnPlay.setText("播放");
        btnPlay.setEnabled(false);//按鈕不能按,準備好才能案
        btnStop.setEnabled(false);

        try {
            mper.reset();
            mper.setDataSource(this, uri);//指定影音來源
            mper.setLooping(ckbLoop.isChecked());
            mper.prepareAsync();
        }catch (Exception e){
            tos.setText("指定音樂檔錯誤"+e.toString());
            tos.show();
        }
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        btnPlay.setEnabled(true);

    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        mper.seekTo(0);//播放位置設定
        btnPlay.setText("播放");
        btnStop.setEnabled(false);
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        tos.setText("發生錯誤，停止播放");
        tos.show();
        return true;
    }

    @Override
    public void onPause(){
        super.onPause();

        if(mper.isPlaying()){
            btnPlay.setText("繼續");
            mper.pause();
        }
    }

    @Override
    protected  void onDestroy(){
        mper.release();
        super.onDestroy();
    }

}
