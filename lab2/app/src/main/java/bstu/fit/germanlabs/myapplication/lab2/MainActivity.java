package bstu.fit.germanlabs.myapplication.lab2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    EditText input;
    Button btn;
    TextView out;
    ListView list;
    String command,outp="";
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                ShellExecuter exe = new ShellExecuter();
                command = input.getText().toString();
                outp = exe.Executer(command);
                ArrayList<String> info = new ArrayList<String>();
                switch(command){
                    case "date":
                        outp=outp.replaceAll(" ","");
                        Integer indexDate=outp.indexOf(":");
                        String time= outp.substring(indexDate-2,indexDate+6);
                        info.add(time);
                        setList(info);
                        break;
                    default:
                   info.add(outp);
                    setList(info);
                }
            }
        });

    }
    public void setList(ArrayList info){
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, info);
        list.setAdapter(adapter);
    }
    public void init(){
        input = findViewById(R.id.txt);
        btn = findViewById(R.id.btn);
        list=findViewById(R.id.out);
        File file= new File(getFilesDir(),"test.txt");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
