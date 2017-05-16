package com.yp9649.atm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by 9649 on 2017/4/17.
 */
public class MyDBHelper extends SQLiteOpenHelper{
    private static MyDBHelper instance;
    private MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static MyDBHelper getInstance(Context ctx)
    {
        if (instance==null)
        {
            instance = new MyDBHelper(ctx, "expense.db",null,1);
        }
            return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       /* String Create_table = " create table test (" +
                "(_id INTEGER PRIMARY KEY NOT NULL ," +
                "cda DATETIME NOT NULL," +
                "info VARCHAR, " +
                "amount INTEGER);";*/

        String Create_table = "create table main.exp ("
                + "_id INTEGER PRIMARY KEY,"
                + "cdate DATETIME,"
                + "info VARCHAR,"
                + "amount INTEGER"
                + ");";

        db.execSQL(Create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
