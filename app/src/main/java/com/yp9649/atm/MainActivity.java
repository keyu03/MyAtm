package com.yp9649.atm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    boolean logon = false;
    public static final int FUNC_LOGIN = 1;
    String[] func = {"餘額查詢","交易明細","最新消息","投資理財","離開"};
    private ListView list;
    int[] icons = {R.drawable.func_balance,
            R.drawable.func_history,
            R.drawable.func_news,
            R.drawable.func_finance,
            R.drawable.func_exit};
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        GridView grid = (GridView) findViewById(R.id.grid);
        grid.setOnItemClickListener(this);

        if (requestCode == FUNC_LOGIN)
        {
            if (resultCode == RESULT_OK)
            {
                String uid = data.getStringExtra("LOGIN_USERID");
                String pw = data.getStringExtra("LOGIN_PASSWD");
                Log.d("RESULT",uid+"/"+pw);
                Toast.makeText(this,(uid+"/"+pw),Toast.LENGTH_LONG).show();
            }else
            {
                finish();
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!logon)
        {
            Intent intent = new Intent(this, LogonActivity.class);
            startActivityForResult(intent,FUNC_LOGIN);
        }

        //產生list
        list = (ListView) findViewById(R.id.list);

        //產生Adapter
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,func);
        list.setAdapter(adapter);

        Spinner notify = (Spinner) findViewById(R.id.notify_spinner);
        final ArrayAdapter<CharSequence> nAdapter = ArrayAdapter.createFromResource(this,R.array.notify_array,R.layout.support_simple_spinner_dropdown_item);
        notify.setAdapter(nAdapter);
        notify.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,nAdapter.getItem(position),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        GridView grid = (GridView) findViewById(R.id.grid);
        IconAdapter gAdapter = new IconAdapter();
        grid.setAdapter(gAdapter);

    }

    //新增一個adapter
    class IconAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return func.length;
        }

        @Override
        public Object getItem(int position) {
            return func[position];
        }

        @Override
        public long getItemId(int position) {
            return icons[position];
        }

        @Override
        //展示項目
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            if (row == null){
                row = getLayoutInflater().inflate(R.layout.item_row,null);
                ImageView image = (ImageView) row.findViewById(R.id.item_image);
                TextView text = (TextView) row.findViewById(R.id.item_text);
                image.setImageResource(icons[position]);
                text.setText(func[position]);
            }
            return row;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            Toast.makeText(this,"test",Toast.LENGTH_LONG).show();
            return true;
        }
        return  super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch ((int)id){
            case R.drawable.func_balance:
                break;
            case R.drawable.func_history:
                break;
            case R.drawable.func_news:
                break;
            case R.drawable.func_finance:
                startActivity(new Intent(this,FinanceActivity.class));
            case R.drawable.func_exit:
                break;
        }
    }
}
