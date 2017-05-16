package com.yp9649.atm;

import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LogonActivity extends AppCompatActivity {

    private EditText edUserid;
    private EditText edPasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logon);
        edUserid = (EditText) findViewById(R.id.userId);
        SharedPreferences setting = getSharedPreferences("atm",MODE_PRIVATE);
        edUserid.setText(setting.getString("PREF_USERID",""));
    }

    public void login(View v)
    {
        edUserid = (EditText) findViewById(R.id.userId);
        edPasswd = (EditText) findViewById(R.id.passwd);

        String uid = edUserid.getText().toString();
        String pw = edPasswd.getText().toString();

        if (uid.equals("keyu") && pw.equals("1234")) {
            SharedPreferences setting = getSharedPreferences("atm",MODE_PRIVATE);
            setting.edit()
                    .putString("PREF_USERID",uid)
                    .commit();
            Toast.makeText(this,"登入成功",Toast.LENGTH_LONG).show();
            getIntent().putExtra("LOGIN_USERID",uid);
            getIntent().putExtra("LOGIN_PASSWD",pw);
            setResult(RESULT_OK, getIntent());
            finish();
        }else
        {
            new AlertDialog.Builder(this).setTitle("ATM").setMessage("登入失敗").setPositiveButton("OK",null).show();
        }
    }

    public void cancel(View v)
    {

    }
}
