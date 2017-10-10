package com.example.win81.sql_test;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    static final String DB_NAME = "HotlineDB";
    static final String TB_NAME = "hotlist";
    static final int MAX = 8;
    static final String[] FROM = new String[]{"name","phone","email"};

    SQLiteDatabase db;
    Cursor cur;
    SimpleCursorAdapter adapter;
    EditText etName,etPhone,etEmail;
    Button btInsert,btUpdate,btDelete;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText)findViewById(R.id.etName);
        etPhone = (EditText)findViewById(R.id.etPhone);
        etEmail = (EditText)findViewById(R.id.etEmail);
        btInsert = (Button)findViewById(R.id.btInsert);
        btUpdate = (Button)findViewById(R.id.btUpdate);
        btDelete = (Button)findViewById(R.id.btDelete);

        db = openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE,null);

        String createTable = "CREATE TABLE IF NOT EXISTS "+ TB_NAME +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR(32),"+
                "phone VARCHAR(16),"+
                "email VARCHAR(32))";

        db.execSQL(createTable);
        cur = db.rawQuery("SELECT * FROM " + TB_NAME,null);

        if(cur.getCount() == 0){
            addData("空白公司","空白電話","空白Email");
        }

        adapter = new SimpleCursorAdapter(this,R.layout.item,cur,FROM,new int[]{R.id.name,R.id.phone,R.id.email},0);

        lv = (ListView)findViewById(R.id.lv);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
        requery();


    }

    private void addData(String name,String phone,String email){
        ContentValues cv = new ContentValues(3);
        cv.put(FROM[0],name);
        cv.put(FROM[1],phone);
        cv.put(FROM[2],email);

        db.insert(TB_NAME,null,cv);
    }

    private void update(String name,String phone,String email,int id){
        ContentValues cv = new ContentValues(3);
        cv.put(FROM[0],name);
        cv.put(FROM[1],phone);
        cv.put(FROM[2],email);

        db.update(TB_NAME,cv,"_id="+id,null);
    }

    private void requery(){
        cur = db.rawQuery("SELECT * FROM " + TB_NAME,null);
        adapter.changeCursor(cur);
        if(cur.getCount() == MAX)
            btInsert.setEnabled(false);
        else
            btInsert.setEnabled(true);

        btUpdate.setEnabled(false);
        btDelete.setEnabled(false);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        cur.moveToPosition(position);
        etName.setText(cur.getString(cur.getColumnIndex(FROM[0])));
        etPhone.setText(cur.getString(cur.getColumnIndex(FROM[1])));
        etEmail.setText(cur.getString(cur.getColumnIndex(FROM[2])));

        btUpdate.setEnabled(true);
        btDelete.setEnabled(true);
    }

    public void onInsertUpdate(View v){
        String nameStr = etName.getText().toString().trim();
        String phoneStr = etPhone.getText().toString().trim();
        String emailStr = etEmail.getText().toString().trim();

        if(nameStr.length() == 0 || phoneStr.length() == 0 || emailStr.length() ==0)
            return;

        if(v.getId() == R.id.btUpdate)
            update(nameStr,phoneStr,emailStr,cur.getInt(0));
        else
            addData(nameStr,phoneStr,emailStr);

        requery();
    }

    public void onDelete(View v){
        db.delete(TB_NAME,"_id="+cur.getInt(0),null);
        requery();
    }

    public void call(View v){
        String uri = "tel:"+cur.getString(cur.getColumnIndex(FROM[1]));
        Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(it);
    }

    public void mail(){
        String uri = "mailto:"+cur.getString(cur.getColumnIndex(FROM[2]));
        Intent it = new Intent(Intent.ACTION_SENDTO, Uri.parse(uri));
        startActivity(it);

    }
}
