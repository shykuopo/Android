package tw.com.flag.toolguy;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Win8.1 on 2016/12/27.
 */

public class shot  extends AppCompatActivity {

    private ImageView mA1,mA2,mA3,mB1,mB2,mB3,mC1,mC2,mC3,mD1,mD2,mD3,mE1,mE2,mE3;
    private ImageButton mButton1,mButton2,mButton3;
    private TextView mTime ;
    private Button mStar;
    private boolean Star = false;
    private long stopTime=30;
    int [][] box;
    double point = 0;



    private SoundPool spool;
    private int sourceid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shot);
        spool = new SoundPool(10, AudioManager.STREAM_MUSIC, 5);
        sourceid = spool.load(this, R.raw.bomb, 1);

        mA1 = (ImageView)findViewById(R.id.A1);
        mA2 = (ImageView)findViewById(R.id.A2);
        mA3 = (ImageView)findViewById(R.id.A3);
        mB1 = (ImageView)findViewById(R.id.B1);
        mB2 = (ImageView)findViewById(R.id.B2);
        mB3 = (ImageView)findViewById(R.id.B3);
        mC1 = (ImageView)findViewById(R.id.C1);
        mC2 = (ImageView)findViewById(R.id.C2);
        mC3 = (ImageView)findViewById(R.id.C3);
        mD1 = (ImageView)findViewById(R.id.D1);
        mD2 = (ImageView)findViewById(R.id.D2);
        mD3 = (ImageView)findViewById(R.id.D3);
        mE1 = (ImageView)findViewById(R.id.E1);
        mE2 = (ImageView)findViewById(R.id.E2);
        mE3 = (ImageView)findViewById(R.id.E3);
        mButton1 = (ImageButton)findViewById(R.id.button1);
        mButton2 = (ImageButton)findViewById(R.id.button2);
        mButton3 = (ImageButton)findViewById(R.id.button3);
        mTime = (TextView)findViewById(R.id.countDown);


        mStar = (Button)findViewById(R.id.star);
        box = new int[3][5];

        mA1.setImageResource(R.drawable.dog);
        mA2.setImageResource(R.drawable.dog);
        mA3.setImageResource(R.drawable.dog);
        mB1.setImageResource(R.drawable.dog);
        mB2.setImageResource(R.drawable.dog);
        mB3.setImageResource(R.drawable.dog);
        mC1.setImageResource(R.drawable.dog);
        mC2.setImageResource(R.drawable.dog);
        mC3.setImageResource(R.drawable.dog);
        mD1.setImageResource(R.drawable.dog);
        mD2.setImageResource(R.drawable.dog);
        mD3.setImageResource(R.drawable.dog);
        mE1.setImageResource(R.drawable.dog);
        mE2.setImageResource(R.drawable.dog);
        mE3.setImageResource(R.drawable.dog);
        mA1.setVisibility(View.INVISIBLE);
        mA2.setVisibility(View.INVISIBLE);
        mA3.setVisibility(View.INVISIBLE);
        mB1.setVisibility(View.INVISIBLE);
        mB2.setVisibility(View.INVISIBLE);
        mB3.setVisibility(View.INVISIBLE);
        mC1.setVisibility(View.INVISIBLE);
        mC2.setVisibility(View.INVISIBLE);
        mC3.setVisibility(View.INVISIBLE);
        mD1.setVisibility(View.INVISIBLE);
        mD2.setVisibility(View.INVISIBLE);
        mD3.setVisibility(View.INVISIBLE);
        mE1.setVisibility(View.INVISIBLE);
        mE2.setVisibility(View.INVISIBLE);
        mE3.setVisibility(View.INVISIBLE);
    }
    public void Star(View v){
        Star = true;
        box=begin();
        showState();
        new CountDownTimer(stopTime*1000, 1000) {
            @Override
            public void onFinish() {
                mTime.setText("結束");
                Star = false;
                mStar.setText(""+point+"分");
                //PP += point;
                point = 0;
            }

            @Override
            public void onTick(long millisUntilFinished) {
                mTime.setText("" + millisUntilFinished / 1000 + "秒");


            }
        }.start();

    };
    public void button1(View v){
        if(Star == true) {

            if (box[0][0] == 1) {
                playSud(0);
                for (int j = 0; j < 4; j++) {
                    for (int i = 0; i < 3; i++) {
                        box[i][j] = box[i][j + 1];
                    }
                }
                int num = (int) (Math.random() * 3);
                box[0][4] = box[1][4] = box[2][4] = 0;
                box[num][4] = 1;
                showState();
                point ++;
            } else {


                try {

                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }

        }

    };
    public void button2(View v){
        if(Star == true) {

            if (box[1][0] == 1) {
                playSud(0);
                for (int j = 0; j < 4; j++) {
                    for (int i = 0; i < 3; i++) {
                        box[i][j] = box[i][j + 1];
                    }
                }
                int num = (int) (Math.random() * 3);
                box[0][4] = box[1][4] = box[2][4] = 0;
                box[num][4] = 1;
                showState();
                point ++;
            } else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }

    };
    public void button3(View v){
        if(Star == true) {

            if (box[2][0] == 1) {
                playSud(0);
                for (int j = 0; j < 4; j++) {
                    for (int i = 0; i < 3; i++) {
                        box[i][j] = box[i][j + 1];
                    }
                }
                int num = (int) (Math.random() * 3);
                box[0][4] = box[1][4] = box[2][4] = 0;
                box[num][4] = 1;
                showState();
                point ++;
            } else {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    };

    public int[][] begin(){
        int arr[][]=new int [3][5];
        for(int i =0;i<5;i++){
            int num = (int) (Math.random() * 3);
            arr[0][i]=arr[1][i]=arr[2][i]=0;
            arr[num][i] = 1;
        }
        return arr;
    }
    public void showState(){


        if (box[0][0]==1){
            mA1.setVisibility(View.VISIBLE);
            mA2.setVisibility(View.INVISIBLE);
            mA3.setVisibility(View.INVISIBLE);
        }
        else if(box[1][0]==1){
            mA2.setVisibility(View.VISIBLE);
            mA1.setVisibility(View.INVISIBLE);
            mA3.setVisibility(View.INVISIBLE);
        }
        else if (box[2][0]==1){
            mA3.setVisibility(View.VISIBLE);
            mA1.setVisibility(View.INVISIBLE);
            mA2.setVisibility(View.INVISIBLE);
        }

        if (box[0][1]==1){
            mB1.setVisibility(View.VISIBLE);
            mB2.setVisibility(View.INVISIBLE);
            mB3.setVisibility(View.INVISIBLE);
        }
        else if (box[1][1]==1){
            mB2.setVisibility(View.VISIBLE);
            mB3.setVisibility(View.INVISIBLE);
            mB1.setVisibility(View.INVISIBLE);
        }
        else if (box[2][1]==1){
            mB3.setVisibility(View.VISIBLE);
            mB1.setVisibility(View.INVISIBLE);
            mB2.setVisibility(View.INVISIBLE);
        }

        if (box[0][2]==1){
            mC1.setVisibility(View.VISIBLE);
            mC2.setVisibility(View.INVISIBLE);
            mC3.setVisibility(View.INVISIBLE);
        }
        else if (box[1][2]==1){
            mC2.setVisibility(View.VISIBLE);
            mC1.setVisibility(View.INVISIBLE);
            mC3.setVisibility(View.INVISIBLE);
        }
        else if (box[2][2]==1){
            mC3.setVisibility(View.VISIBLE);
            mC1.setVisibility(View.INVISIBLE);
            mC2.setVisibility(View.INVISIBLE);
        }

        if (box[0][3]==1){
            mD1.setVisibility(View.VISIBLE);
            mD2.setVisibility(View.INVISIBLE);
            mD3.setVisibility(View.INVISIBLE);
        }
        else if (box[1][3]==1){
            mD2.setVisibility(View.VISIBLE);
            mD1.setVisibility(View.INVISIBLE);
            mD3.setVisibility(View.INVISIBLE);
        }
        else if (box[2][3]==1){
            mD3.setVisibility(View.VISIBLE);
            mD1.setVisibility(View.INVISIBLE);
            mD2.setVisibility(View.INVISIBLE);
        }

        if (box[0][4]==1){
            mE1.setVisibility(View.VISIBLE);
            mE2.setVisibility(View.INVISIBLE);
            mE3.setVisibility(View.INVISIBLE);
        }
        else if (box[1][4]==1){
            mE2.setVisibility(View.VISIBLE);
            mE1.setVisibility(View.INVISIBLE);
            mE3.setVisibility(View.INVISIBLE);
        }
        else if (box[2][4]==1){
            mE3.setVisibility(View.VISIBLE);
            mE1.setVisibility(View.INVISIBLE);
            mE2.setVisibility(View.INVISIBLE);
        }

    }
    public void playSud(int repeatTime){
        AudioManager am = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);


        float audMaxVolumn = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        spool.play(sourceid, 1.0F, 1.0F, 1, repeatTime, 1);

    }
    public void next(View v){

    }
}
