package com.example.win81.memorandum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Edit extends MainActivity {
    EditText edt;
    TextView txv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edt = (EditText)findViewById(R.id.editText2);
        txv = (TextView)findViewById(R.id.textView);

        Intent it= getIntent();
        String s = it.getStringExtra("memo");

        txv.setText(s.substring(0,2));
        if(s.length()>3)
            edt.setText(s.substring(2));


    }

    public void onCancel(View v){
        setResult(RESULT_CANCELED);
        finish();
    }
    public void onSave(View v){
        Intent it2 = new Intent();
        it2.putExtra("memo",txv.getText() + "" +edt.getText());

        setResult(RESULT_OK,it2);
        finish();
    }
}
