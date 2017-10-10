package com.example.win81.memorandum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{

    String[] aMemo = {
      "1.按一下可以編輯備忘錄", "2.長按可以清除備忘錄","3.","4.","5.","6."
    };
    ListView lv;
    ArrayAdapter<String> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView)findViewById(R.id.listView);
        aa = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,aMemo);
        lv.setAdapter(aa);

        lv.setOnItemLongClickListener(this);
        lv.setOnItemClickListener(this);

    }

    public void onItemClick(AdapterView<?> a, View v ,int pos ,long id){
        Intent it = new Intent(this,Edit.class);
        it.putExtra("memo",aMemo[pos]);
        startActivityForResult(it,pos);
    }

    public boolean onItemLongClick(AdapterView<?> a,View v ,int pos ,long id){
        aMemo[pos] = (pos+1)+".";
        aa.notifyDataSetChanged();
        return true;
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent it){
        if(resultCode == RESULT_OK){
            aMemo[requestCode] = it.getStringExtra("memo");
            aa.notifyDataSetChanged();
        }
    }
}
