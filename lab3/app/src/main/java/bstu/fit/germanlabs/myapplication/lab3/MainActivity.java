package bstu.fit.germanlabs.myapplication.lab3;
import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {
    private final static String NOTES="notes.txt";
    private final static String DICTIONARY="dictionary.txt";
    private EditText editor;
    public Button btsave;
    public Button btread;
    Button btn;
    String p;
    List<String> list;
    public Button add,read;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        init();

        btn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        btread.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                try {
                    InputStream in=openFileInput(NOTES);

                    if (in!=null) {
                        InputStreamReader tmp=new InputStreamReader(in);
                        BufferedReader reader=new BufferedReader(tmp);
                        String str="";
                        StringBuilder buf=new StringBuilder();

                        while ((str = reader.readLine()) != null) {
                            buf.append(str+"\n");
                        }

                        in.close();
                        editor.setText(buf.toString());
                    }
                }
                catch (Exception e) {
                    // that's OK, we probably haven't created it yet

                    try {
                        FileOutputStream mOutput = openFileOutput(NOTES, Activity.MODE_PRIVATE);
                        String data = "THIS DATA WRITTEN TO A FILE FIRST TIME!!!";
                        mOutput.write(data.getBytes());
                        mOutput.close();
                    } catch (FileNotFoundException ea) {
                        ea.printStackTrace();
                    } catch (IOException ee) {
                        ee.printStackTrace();
                    }

                }
            }
        });



        btsave.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                try {
                    FileOutputStream mOutput = openFileOutput(NOTES, Activity.MODE_PRIVATE);
                    String data = editor.getText().toString();
                    mOutput.write(data.getBytes());
                    mOutput.close();
                    alterDialog("Успешная запись");
                } catch (FileNotFoundException ea) {
                    ea.printStackTrace();
                    alterDialog("Ощибка"+ea.getMessage());
                } catch (IOException ee) {
                    ee.printStackTrace();
                    alterDialog("Ощибка"+ee.getMessage());
                }

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Map<String, String> states = new HashMap<String, String>();
                String info = editor.getText().toString();
                boolean result = info.matches("^[а-яА-Я]+:[a-zA-Z]+");
                if (result == true) {
                   // states.put(info.substring(0,info.indexOf(':')),info.substring(info.indexOf(':')));
                    writeFile(info+";"+"\n");
                    editor.setText(editor.getText().toString().substring(0,editor.getText().toString().indexOf(':')));
                }
                else{
                    alterDialog("Некорректная строка");
                }
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readFile();

            }
        });

    }
    public void alterDialog(String message){
        AlertDialog.Builder b= new AlertDialog.Builder(MainActivity.this);
        b.setTitle(message).setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog a=b.create();
        a.show();
    }
    public void init(){
        btsave=(Button)findViewById(R.id.bsave);
        editor=(EditText)findViewById(R.id.editor);
        editor.setBackgroundColor(240);
        btn=(Button)findViewById(R.id.close);
        btread=(Button)findViewById(R.id.bread);
        add=findViewById(R.id.button2);
        read=findViewById(R.id.button3);
    }
    private void writeFile(String info)
    {
       File file =new File(super.getFilesDir()+DICTIONARY);
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(info);
            Toast.makeText(this, "Данные записаны", Toast.LENGTH_SHORT).show();
            fileWriter.close();
        } catch (IOException ex) {
            Toast.makeText(this,ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    private String readFile() {
        try {
            File file = new File(super.getFilesDir()+DICTIONARY);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            Integer index = line.indexOf(editor.getText().toString());
            while (line != null) {
                line=reader.readLine();
            }
            if(index!=-1){
                p=p.substring(p.indexOf(':'),p.indexOf("\n"));
            }
            else{
                alterDialog("Не найдено");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    /*
      File  file =new File(super.getFilesDir()+DICTIONARY);
        try{
            FileReader fileReader = new FileReader(file);
            FileInputStream fw=new FileInputStream(file);
            char[] nw=new char[fw.available()];
            fileReader.read(nw);
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < nw.length; ++i) {
                builder.append(nw[i]);
            }
            p=builder.toString();
            alterDialog(p);
            fileReader.close();
        }
        catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return "";
    }*/
        return "";
    }

}
