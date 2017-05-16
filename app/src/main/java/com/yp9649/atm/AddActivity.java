package com.yp9649.atm;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    private EditText edDate;
    private EditText edInfo;
    private EditText edAmount;

    private MyDBHelper helper;

    @Override
    //測試修改後第二次儲存
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        findViews();
        //helper = new MyDBHelper(this,"expense.db",null,1);
        helper = MyDBHelper.getInstance(this);
    }

    private void findViews() {
        edDate = (EditText) findViewById(R.id.ed_date);
        edInfo = (EditText) findViewById(R.id.ed_info);
        edAmount = (EditText) findViewById(R.id.ed_amount);
    }

    public void add(View v)
    {
        String cdate = edDate.getText().toString();
        String info = edInfo.getText().toString();
        int amount = Integer.parseInt(edAmount.getText().toString());
        ContentValues values = new ContentValues();

        values.put("cdate", cdate);
        values.put("info", info);
        values.put("amount",amount);

        Toast.makeText(this,info,Toast.LENGTH_LONG).show();
        long id = helper.getWritableDatabase().insert("exp",null,values);
        //Log.d("ADD",id+"");
    }
}
