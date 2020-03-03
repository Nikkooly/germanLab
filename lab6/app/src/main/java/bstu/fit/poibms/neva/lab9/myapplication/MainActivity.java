package bstu.fit.poibms.neva.lab9.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;;

public class MainActivity extends Activity {

    public EditText editorE;
    public static DBHelper dbHelper;
    public String query;
    Cursor c;
    TextView textView;
    ArrayList<String> info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.close);
        editorE = (EditText) findViewById(R.id.editor);
        dbHelper = new DBHelper(this);
        info=  new ArrayList<String>();
        btn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });


        final DBHelper db = new DBHelper(this);

        Button but1 = (Button) findViewById(R.id.insert);

        but1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                db.add(new Values(editorE.getText().toString()));
                Toast.makeText(getApplicationContext(), "Succesfully write", Toast.LENGTH_SHORT).show();
            }
        });

        Button but2 = (Button) findViewById(R.id.Read);

        but2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                load();
            }
        });

    }
    public void load() {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        query = "select name from language";
        c = database.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            info.add(c.getString(0));
            c.moveToNext();
        }
        String p =info.toString();
        p= p.replace('[',' ');
        p= p.replace(']',' ');
        p= p.replace(',','\n');
        editorE.setText(p);
        c.close();
    }
}