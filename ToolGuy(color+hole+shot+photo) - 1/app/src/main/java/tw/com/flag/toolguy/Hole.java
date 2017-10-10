package tw.com.flag.toolguy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Hole extends AppCompatActivity {
    private ImageView mmouse1, mmouse2, mmouse3,mmouse4,mmouse5,mmouse6,mmouse7,mmouse8,mmouse9;
    boolean[] bhole;
    private boolean Start = false;
    private TextView mtime,mscore;
    Button next;
    public Thread t1;
    public int i;
    private ImageView Img2;
    private Button startbtn;
    public static Bitmap bmp_Hole;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hole);
        mmouse1 = (ImageView) findViewById(R.id.mouse1);
        mmouse2 = (ImageView) findViewById(R.id.mouse2);
        mmouse3 = (ImageView) findViewById(R.id.mouse3);
        mmouse4 = (ImageView) findViewById(R.id.mouse4);
        mmouse5 = (ImageView) findViewById(R.id.mouse5);
        mmouse6 = (ImageView) findViewById(R.id.mouse6);
        mmouse7 = (ImageView) findViewById(R.id.mouse7);
        mmouse8 = (ImageView) findViewById(R.id.mouse8);
        mmouse9 = (ImageView) findViewById(R.id.mouse9);
        Img2=(ImageView)findViewById(R.id.pic);
        Img2.setImageBitmap(bmp_Hole);
        startbtn=(Button)findViewById(R.id.start);
        next=(Button)findViewById(R.id.nxt);



        mtime=(TextView)findViewById(R.id.time);
        mscore=(TextView)findViewById(R.id.score);
        mtime.setText("");
        mscore.setText("");

        mmouse1.setImageResource(R.drawable.zombie);
        mmouse2.setImageResource(R.drawable.zombie);
        mmouse3.setImageResource(R.drawable.zombie);
        mmouse4.setImageResource(R.drawable.zombie);
        mmouse5.setImageResource(R.drawable.zombie);
        mmouse6.setImageResource(R.drawable.zombie);
        mmouse7.setImageResource(R.drawable.zombie);
        mmouse8.setImageResource(R.drawable.zombie);
        mmouse9.setImageResource(R.drawable.zombie);

        mmouse1.setVisibility(View.INVISIBLE);
        mmouse2.setVisibility(View.INVISIBLE);
        mmouse3.setVisibility(View.INVISIBLE);
        mmouse4.setVisibility(View.INVISIBLE);
        mmouse5.setVisibility(View.INVISIBLE);
        mmouse6.setVisibility(View.INVISIBLE);
        mmouse7.setVisibility(View.INVISIBLE);
        mmouse8.setVisibility(View.INVISIBLE);
        mmouse9.setVisibility(View.INVISIBLE);

        bhole = new boolean[9];
    }




    public void startClick(View v) {
        startbtn.setEnabled(false);
        new CountDownTimer(30 * 1000, 1000) {
            @Override
            public void onFinish() {

                Start = false;
                mtime.setText("時間到~");
                mscore.setText(i + "分");
                i = 0;
                startbtn.setEnabled(true);
                next.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTick(long millisUntilFinished) {

                mtime.setText("剩下" + millisUntilFinished / 1000 + "秒");
                mscore.setText(i + "分");
            }
        }.start();


        t1 = new Thread() {
            @Override
            public void run() {
                Start = true;
                while (Start == true) {
                    final int tt = (int) (Math.random() * 9);

                    bhole[tt] = true;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            contact();
                        }
                    });
                    try {
                        if (bhole[tt] == true) {
                            sleep(2000);
                            bhole[tt] = false;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    contact();
                                }
                            });
                        }
                    } catch (InterruptedException e) {
                        if (bhole[tt] == false) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    contact();
                                    i++;
                                }
                            });
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        t1.start();
    }



    public void contact(){
        if (bhole[0]==true){
            mmouse1.setVisibility(View.VISIBLE);
            mmouse2.setVisibility(View.INVISIBLE);
            mmouse3.setVisibility(View.INVISIBLE);
            mmouse4.setVisibility(View.INVISIBLE);
            mmouse5.setVisibility(View.INVISIBLE);
            mmouse6.setVisibility(View.INVISIBLE);
            mmouse7.setVisibility(View.INVISIBLE);
            mmouse8.setVisibility(View.INVISIBLE);
            mmouse9.setVisibility(View.INVISIBLE);
        }
        else if (bhole[1]==true){
            mmouse2.setVisibility(View.VISIBLE);
            mmouse1.setVisibility(View.INVISIBLE);
            mmouse3.setVisibility(View.INVISIBLE);
            mmouse4.setVisibility(View.INVISIBLE);
            mmouse5.setVisibility(View.INVISIBLE);
            mmouse6.setVisibility(View.INVISIBLE);
            mmouse7.setVisibility(View.INVISIBLE);
            mmouse8.setVisibility(View.INVISIBLE);
            mmouse9.setVisibility(View.INVISIBLE);
        }
        else if (bhole[2]==true){
            mmouse3.setVisibility(View.VISIBLE);
            mmouse1.setVisibility(View.INVISIBLE);
            mmouse2.setVisibility(View.INVISIBLE);
            mmouse4.setVisibility(View.INVISIBLE);
            mmouse5.setVisibility(View.INVISIBLE);
            mmouse6.setVisibility(View.INVISIBLE);
            mmouse7.setVisibility(View.INVISIBLE);
            mmouse8.setVisibility(View.INVISIBLE);
            mmouse9.setVisibility(View.INVISIBLE);
        }
        else if (bhole[3]==true){
            mmouse1.setVisibility(View.INVISIBLE);
            mmouse2.setVisibility(View.INVISIBLE);
            mmouse3.setVisibility(View.INVISIBLE);
            mmouse4.setVisibility(View.VISIBLE);
            mmouse5.setVisibility(View.INVISIBLE);
            mmouse6.setVisibility(View.INVISIBLE);
            mmouse7.setVisibility(View.INVISIBLE);
            mmouse8.setVisibility(View.INVISIBLE);
            mmouse9.setVisibility(View.INVISIBLE);
        }
        else if (bhole[4]==true){
            mmouse1.setVisibility(View.INVISIBLE);
            mmouse2.setVisibility(View.INVISIBLE);
            mmouse3.setVisibility(View.INVISIBLE);
            mmouse4.setVisibility(View.INVISIBLE);
            mmouse5.setVisibility(View.VISIBLE);
            mmouse6.setVisibility(View.INVISIBLE);
            mmouse7.setVisibility(View.INVISIBLE);
            mmouse8.setVisibility(View.INVISIBLE);
            mmouse9.setVisibility(View.INVISIBLE);
        }
        else if (bhole[5]==true){
            mmouse1.setVisibility(View.INVISIBLE);
            mmouse2.setVisibility(View.INVISIBLE);
            mmouse3.setVisibility(View.INVISIBLE);
            mmouse4.setVisibility(View.INVISIBLE);
            mmouse5.setVisibility(View.INVISIBLE);
            mmouse6.setVisibility(View.VISIBLE);
            mmouse7.setVisibility(View.INVISIBLE);
            mmouse8.setVisibility(View.INVISIBLE);
            mmouse9.setVisibility(View.INVISIBLE);
        }
        else if (bhole[6]==true){
            mmouse1.setVisibility(View.INVISIBLE);
            mmouse2.setVisibility(View.INVISIBLE);
            mmouse3.setVisibility(View.INVISIBLE);
            mmouse4.setVisibility(View.INVISIBLE);
            mmouse5.setVisibility(View.INVISIBLE);
            mmouse6.setVisibility(View.INVISIBLE);
            mmouse7.setVisibility(View.VISIBLE);
            mmouse8.setVisibility(View.INVISIBLE);
            mmouse9.setVisibility(View.INVISIBLE);
        }
        else if (bhole[7]==true){
            mmouse1.setVisibility(View.INVISIBLE);
            mmouse2.setVisibility(View.INVISIBLE);
            mmouse3.setVisibility(View.INVISIBLE);
            mmouse4.setVisibility(View.INVISIBLE);
            mmouse5.setVisibility(View.INVISIBLE);
            mmouse6.setVisibility(View.INVISIBLE);
            mmouse7.setVisibility(View.INVISIBLE);
            mmouse8.setVisibility(View.VISIBLE);
            mmouse9.setVisibility(View.INVISIBLE);
        }
        else if (bhole[8]==true){
            mmouse1.setVisibility(View.INVISIBLE);
            mmouse2.setVisibility(View.INVISIBLE);
            mmouse3.setVisibility(View.INVISIBLE);
            mmouse4.setVisibility(View.INVISIBLE);
            mmouse5.setVisibility(View.INVISIBLE);
            mmouse6.setVisibility(View.INVISIBLE);
            mmouse7.setVisibility(View.INVISIBLE);
            mmouse8.setVisibility(View.INVISIBLE);
            mmouse9.setVisibility(View.VISIBLE);
        }
    }
    public void btn1Click(View V){


        bhole[0]=false;
        t1.interrupt();
    }
    public void btn2Click(View V){


        bhole[1]=false;
        t1.interrupt();
    }
    public void btn3Click(View V){


        bhole[2]=false;
        t1.interrupt();
    }
    public void btn4Click(View V){


        bhole[3]=false;
        t1.interrupt();
    }
    public void btn5Click(View V){


        bhole[4]=false;
        t1.interrupt();
    }
    public void btn6Click(View V){


        bhole[5]=false;
        t1.interrupt();
    }
    public void btn7Click(View V){


        bhole[6]=false;
        t1.interrupt();
    }
    public void btn8Click(View V){
        bhole[7]=false;
        t1.interrupt();
    }
    public void btn9Click(View V){


        bhole[8]=false;
        t1.interrupt();
    }

    public void goM3(View v){
        Intent m3=new Intent(this,M3.class);
        startActivity(m3);
        finish();
    }
}
