package com.example.win81.homework5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private String NAME ,rec;
    private EditText mNum,mName,mFindName;
    private TextView mResult,mExp;
    private double record;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNum = (EditText)findViewById(R.id.num);
        mName = (EditText)findViewById(R.id.name);
        mFindName = (EditText)findViewById(R.id.findName);
        mResult = (TextView)findViewById(R.id.result);
        mExp = (TextView)findViewById(R.id.exp);

    }

    public void write(View v){
        FileOutputStream fileOut =null;
        BufferedOutputStream bufFileOut = null;
        NAME = mName.getText().toString();

        try{
            fileOut = openFileOutput(NAME, MODE_PRIVATE);
            bufFileOut = new BufferedOutputStream(fileOut);
            bufFileOut.write(mNum.getText().toString().getBytes());
            bufFileOut.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void delete(View v){
        FileOutputStream fileOut = null;
        NAME = mName.getText().toString();

        try{
            fileOut = openFileOutput(NAME,MODE_PRIVATE);
            fileOut.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }



    public void find(View v){
        FileInputStream fileIn = null;
        BufferedInputStream bufFileIn = null;
        NAME = mFindName.getText().toString();

        try {
            fileIn = openFileInput(NAME);
            bufFileIn = new BufferedInputStream(fileIn);

            byte[] bufBytes = new byte[20];


            do {
                int c = bufFileIn.read(bufBytes);
                if (c == -1)
                    break;
                else
                    rec = new String(bufBytes);


            } while (true);

            bufFileIn.close();
        }catch (Exception e){
            Toast.makeText(MainActivity.this, "資訊不正確", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        record = Double.parseDouble(rec);
        mResult.setText(rec);
        mExp.setText(Double.toString(record));
    }
}
