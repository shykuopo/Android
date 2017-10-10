package tw.com.flag.toolguy;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class color extends AppCompatActivity {

    TextView Ch,score,mean,time;
    ImageButton A,B,C,D,E;
    Button S,F;
    int a,b,c;
    int Cnt=0;
    VideoView vdv;
    int pos =0;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_color);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        Ch=(TextView)findViewById(R.id.textView);
        score=(TextView)findViewById(R.id.textView2);
        mean=(TextView)findViewById(R.id.textView3);
        time=(TextView)findViewById(R.id.textView4);
        A=(ImageButton)findViewById(R.id.imageButton);
        B=(ImageButton)findViewById(R.id.imageButton2);
        C=(ImageButton)findViewById(R.id.imageButton3);
        D=(ImageButton)findViewById(R.id.imageButton4);
        E=(ImageButton)findViewById(R.id.imageButton5);
        S=(Button)findViewById(R.id.button7);
        F=(Button)findViewById(R.id.button9);

        uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.jay);
        if(savedInstanceState !=null){
            pos=savedInstanceState.getInt("pos",0);
        }
        vdv=(VideoView)findViewById(R.id.videoView4);
        MediaController mediaCtrl=new MediaController(this);
        vdv.setMediaController(mediaCtrl);
        vdv.setVideoURI(uri);

    }
    public void set (View v){
        a= (int)(Math.random()*5+1);
        b= (int)(Math.random()*5+1);
        c=(int)(Math.random()*2);




        if(c==0)
            mean.setText("請看字的顏色");
        else
            mean.setText("請看字的意義");
        SetColor();
        A.setVisibility(View.VISIBLE);
        B.setVisibility(View.VISIBLE);
        C.setVisibility(View.VISIBLE);
        D.setVisibility(View.VISIBLE);
        E.setVisibility(View.VISIBLE);
        Cnt=0;
        new CountDownTimer(3000,1000){

            @Override
            public void onFinish() {
                time.setText("Done!");
                A.setVisibility(View.INVISIBLE);
                B.setVisibility(View.INVISIBLE);
                C.setVisibility(View.INVISIBLE);
                D.setVisibility(View.INVISIBLE);
                E.setVisibility(View.INVISIBLE);
                S.setVisibility(View.INVISIBLE);
                F.setVisibility(View.VISIBLE);

            }

            @Override
            public void onTick(long millisUntilFinished) {
                time.setText("seconds remaining:"+millisUntilFinished/1000);
            }

        }.start();
        S.setVisibility(View.INVISIBLE);




    }
    public  void SetColor (){
        if(a==1){
            Ch.setText("紅");
            if(b==1){
                Ch.setTextColor(Color.RED);
            }
            else if(b==2){
                Ch.setTextColor(Color.BLACK);
            }
            else if(b==3){
                Ch.setTextColor(Color.YELLOW);
            }
            else if(b==4){
                Ch.setTextColor(Color.BLUE);
            }
            else if(b==5){
                Ch.setTextColor(Color.GREEN);
            }
        }
        else if(a==2){
            Ch.setText("黑");
            if(b==1){
                Ch.setTextColor(Color.RED);
            }
            else if(b==2){
                Ch.setTextColor(Color.BLACK);
            }
            else if(b==3){
                Ch.setTextColor(Color.YELLOW);
            }
            else if(b==4){
                Ch.setTextColor(Color.BLUE);
            }
            else if(b==5){
                Ch.setTextColor(Color.GREEN);
            }

        }
        else if(a==3){
            Ch.setText("黃");
            if(b==1){
                Ch.setTextColor(Color.RED);
            }
            else if(b==2){
                Ch.setTextColor(Color.BLACK);
            }
            else if(b==3){
                Ch.setTextColor(Color.YELLOW);
            }
            else if(b==4){
                Ch.setTextColor(Color.BLUE);
            }
            else if(b==5){
                Ch.setTextColor(Color.GREEN);
            }

        }
        else if(a==4){
            Ch.setText("綠");
            if(b==1){
                Ch.setTextColor(Color.RED);
            }
            else if(b==2){
                Ch.setTextColor(Color.BLACK);
            }
            else if(b==3){
                Ch.setTextColor(Color.YELLOW);
            }
            else if(b==4){
                Ch.setTextColor(Color.BLUE);
            }
            else if(b==5){
                Ch.setTextColor(Color.GREEN);
            }

        }
        else if(a==5){
            Ch.setText("藍");
            if(b==1){
                Ch.setTextColor(Color.RED);
            }
            else if(b==2){
                Ch.setTextColor(Color.BLACK);
            }
            else if(b==3){
                Ch.setTextColor(Color.YELLOW);
            }
            else if(b==4){
                Ch.setTextColor(Color.BLUE);
            }
            else if(b==5){
                Ch.setTextColor(Color.GREEN);
            }

        }
    }

    public void red(View v){
        if(c==0) {
            if (b == 1) {
                Cnt = Cnt + 1;
            }
        }
        else {
            if(a==1){
                Cnt++;
            }
        }
        a= (int)(Math.random()*5+1);
        b= (int)(Math.random()*5+1);
        c=(int)(Math.random()*2);
        if(c==0)
            mean.setText("請看字的顏色");
        else
            mean.setText("請看字的意義");
        score.setText(Integer.toString(Cnt));
        SetColor();
    }
    public void black(View v){
        if(c==0) {
            if (b == 2) {
                Cnt = Cnt + 1;
            }
        }
        else {
            if(a==2){
                Cnt++;
            }
        }
        a= (int)(Math.random()*5+1);
        b= (int)(Math.random()*5+1);
        c=(int)(Math.random()*2);
        if(c==0)
            mean.setText("請看字的顏色");
        else
            mean.setText("請看字的意義");
        score.setText(Integer.toString(Cnt));
        SetColor();
    }
    public void yellow(View v){
        if(c==0) {
            if (b == 3) {
                Cnt = Cnt + 1;
            }
        }
        else {
            if(a==3){
                Cnt++;
            }
        }
        a= (int)(Math.random()*5+1);
        b= (int)(Math.random()*5+1);
        c=(int)(Math.random()*2);
        if(c==0)
            mean.setText("請看字的顏色");
        else
            mean.setText("請看字的意義");
        score.setText(Integer.toString(Cnt));
        SetColor();
    }
    public void green(View v){
        if(c==0) {
            if (b == 4) {
                Cnt = Cnt + 1;
            }
        }
        else {
            if(a==4){
                Cnt++;
            }
        }
        a= (int)(Math.random()*5+1);
        b= (int)(Math.random()*5+1);
        c=(int)(Math.random()*2);
        if(c==0)
            mean.setText("請看字的顏色");
        else
            mean.setText("請看字的意義");
        score.setText(Integer.toString(Cnt));
        SetColor();
    }
    public void blue(View v){
        if(c==0) {
            if (b == 5) {
                Cnt = Cnt + 1;
            }
        }
        else {
            if(a==5){
                Cnt++;
            }
        }
        a= (int)(Math.random()*5+1);
        b= (int)(Math.random()*5+1);
        c=(int)(Math.random()*2);
        if(c==0)
            mean.setText("請看字的顏色");
        else
            mean.setText("請看字的意義");
        score.setText(Integer.toString(Cnt));
        SetColor();
    }
    public void goM2(View v){
        Intent that=new Intent(this,M2.class);

        startActivity(that);
        finish();
    }

//////////////////////////////////////////////////////

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
}
