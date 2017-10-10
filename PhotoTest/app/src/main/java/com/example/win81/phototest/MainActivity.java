package com.example.win81.phototest;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Uri imgUri;
    ImageView imv;
    boolean needRotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imv = (ImageView)findViewById(R.id.imageView);
    }

    public void onGet(View v){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},200);

        }
        else {
            savePhoto();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults){
        if(requestCode == 200){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                savePhoto();
                Toast.makeText(this,"獲得權限",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this,"程式需要寫入全現在能運作",Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void savePhoto(){
        imgUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,new ContentValues());
        Intent it = new Intent("android.media.action.IMAGE_CAPTURE");
        it.putExtra(MediaStore.EXTRA_OUTPUT,imgUri);
        startActivityForResult(it,100);
    }
    public void onPick(View v){
        Intent it = new Intent(Intent.ACTION_GET_CONTENT);
        it.setType("image/*");
        startActivityForResult(it,101);
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == Activity.RESULT_OK ) {
            switch (requestCode) {
                case 100:
                    Intent it = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, imgUri);
                    sendBroadcast(it);
                    break;
                case 101:
                    imgUri = data.getData();
                    break;
            }
            showImg();

        }else{
            Toast.makeText(this, requestCode==100? "沒有拍到照片":"沒有選取照片",Toast.LENGTH_LONG).show();
        }
    }

    void showImg(){
        int iw,ih,vw,vh;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        try{
            BitmapFactory.decodeStream(getContentResolver().openInputStream(imgUri),null,options);
            Toast.makeText(this, "讀取照片成功",Toast.LENGTH_LONG).show();
        }catch (IOException e){
            Toast.makeText(this, "讀取照片資訊時發生錯誤",Toast.LENGTH_LONG).show();
            return;
        }

        iw = options.outWidth;
        ih = options.outHeight;
        vw = imv.getWidth();
        vh = imv.getHeight();

        int scaleFactor ;

        if(iw<ih){
            needRotate = false;
            scaleFactor = Math.min(iw/vw,ih/vh);
        }
        else{
            needRotate = true;
            scaleFactor = Math.min(ih/vw,iw/vh);
        }

        options.inJustDecodeBounds = false;
        options.inSampleSize = scaleFactor;

        Bitmap bmp = null;
        try {
            bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(imgUri),null,options);

        }catch (IOException e){
            Toast.makeText(this, "無法讀取照片",Toast.LENGTH_LONG).show();
        }
        if(needRotate){
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            bmp = Bitmap.createBitmap(bmp,0,0,bmp.getWidth(),bmp.getHeight(),matrix,true);
        }
        imv.setImageBitmap(bmp);

        new AlertDialog.Builder(this)
                .setTitle("圖檔資訊")
                .setMessage("圖檔URI:"+imgUri.toString()+
                "\n原始尺寸:"+iw+"x"+ih+
                "\n載入尺寸:"+bmp.getWidth()+"x"+bmp.getHeight()+
                "\n顯使尺寸:"+vw+"x"+vh)
                .setNeutralButton("關閉",null)
                .show();
    }
}
