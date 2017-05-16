package com.yp9649.atm;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class FinanceActivity extends AppCompatActivity {

    private ListView list;
    private MyDBHelper helper;
    private Cursor c;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setContentView(R.layout.activity_finance);
        list = (ListView) findViewById(R.id.list);
        //helper = new MyDBHelper(this,"expense.db",null,1);
        helper = MyDBHelper.getInstance(this);

        Cursor c = helper.getReadableDatabase().query(
                "exp", null, null, null, null, null, null);

        adapter = new SimpleCursorAdapter(this,
                 R.layout.finance_row,
                 c,
                 new String[] {"_id","cdate","info","amount"},
                 new int[]{R.id.item_id,R.id.item_cdate,R.id.item_info,R.id.item_amount},
                 0);
        list.setAdapter(adapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FinanceActivity.this,AddActivity.class));
            }
        });
    }

}