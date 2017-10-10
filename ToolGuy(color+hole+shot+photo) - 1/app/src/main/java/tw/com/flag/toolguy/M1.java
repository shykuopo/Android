package tw.com.flag.toolguy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

public class M1 extends AppCompatActivity {
    VideoView vdv;
    int pos =0;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m1);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_m1);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.m1);
        if(savedInstanceState !=null){
            pos=savedInstanceState.getInt("pos",0);
        }
        vdv=(VideoView)findViewById(R.id.videoView);
        MediaController mediaCtrl=new MediaController(this);
        vdv.setMediaController(mediaCtrl);
        vdv.setVideoURI(uri);
    }
    protected void onResume(){
        super.onResume();
        vdv.seekTo(pos);
        vdv.start();
    }

    protected void onPause(){
        super.onPause();
        pos=vdv.getCurrentPosition();
        vdv.stopPlayback();
    }

    protected void onSaveInstanceState(Bundle outstate){
        super.onSaveInstanceState(outstate);
        outstate.putInt("pos",pos);
    }

    public void goColor(View v){
        Intent it=new Intent(this, color.class);
        startActivity(it);
        finish();
    }
}
