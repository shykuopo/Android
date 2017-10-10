package tw.com.flag.toolguy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView imv;
    public  Bitmap bmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imv=(ImageView)findViewById(R.id.imageView);
    }

    public void goM1(View v){
        Intent it=new Intent(this,M1.class);
        startActivity(it);
        finish();
    }
    public  void  onGet(View v){
        /*String dir= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        String fname="p"+System.currentTimeMillis()+".jpg";
        imgUri=Uri.parse("file://"+dir+"/"+fname);

        Intent it1=new Intent("android.media.action.IMAGE_CAPTURE");
        it1.putExtra(MediaStore.EXTRA_OUTPUT,imgUri);
        startActivityForResult(it1,100);*/

        Intent it=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(it,100);


    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        /*super.onActivityResult(requestCode,resultCode,data);
        if(resultCode== Activity.RESULT_OK && requestCode==100){

            Bitmap bmp= BitmapFactory.decodeFile(imgUri.getPath());
            Hole.bmp_Hole=bmp;
            imv.setImageBitmap(bmp);

        }
        else{
            Toast.makeText(this,"沒有拍到照片",Toast.LENGTH_LONG).show();

        }*/
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode== Activity.RESULT_OK && requestCode==100){

            Bundle extras = data.getExtras();
            bmp= (Bitmap) extras.get("data");
            Hole.bmp_Hole=bmp;


            imv.setImageBitmap(bmp);

        }
        else{
            Toast.makeText(this,"沒有拍到照片",Toast.LENGTH_LONG).show();

        }
    }
}
