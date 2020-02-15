package bstu.fit.germanlabs.myapplication.lab1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;


public class MainActivity extends Activity {

     EditText username=null;
     EditText  password=null;
     String user="admin",pass="admin",s;
    ArrayList<Integer> check;
     int count=0;
     String p,k,l;
    private Button login;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    { super.onCreate (savedInstanceState);
        setContentView(R.layout.activity_main);
init();
    }
public void init(){
    username = findViewById (R.id.editText1);
    password=(EditText) findViewById (R.id.editText2);
    login = (Button) findViewById (R.id.button1);
    check=new ArrayList<Integer>();
}
    public void login(View view) {
        s=password.getText().toString().trim();
        l=login.getText().toString().trim();
        if(login.getText().toString().length()==0 || s.length()==0){
            Toast.makeText(getApplicationContext(),"Empty field. Please check!",
                    Toast.LENGTH_SHORT).show();
        }
            if (s.length() < pass.length()-1 || s.length() > pass.length()+1) {
                errorCheck(2);
            } else if (s.length() == pass.length()-1) {
                s = s + " ";
                checkSymbols();
                Integer sum = check.get(0) + 1;
                errorCheck(sum);
            } else if (s.length() == pass.length()+1) {
                s = s.substring(0, s.length() - 1);
                checkSymbols();
                Integer sum = check.get(0) + 0;
                errorCheck(sum);
            } else if (s.equals(pass)) {
                Toast.makeText(getApplicationContext(), "You are welcome!",
                        Toast.LENGTH_SHORT).show();
                count = 0;
            } else {
                checkSymbols();
                Integer sum = check.get(0) + check.get(1);
                errorCheck(sum);
            }
            check.clear();
    }
public void checkSymbols() {
    for (int i = 0; i < pass.length(); i++) {
        k = pass.substring(i, i + 1);
        p = s.substring(i, i + 1);
        if (p.equals(k)) {
            check.add(1);
        } else {
            check.add(0);
        }

    }
    Collections.sort(check);
}
public void errorCheck(Integer sum){
    if(sum!=1){
        count++;
        if(count==3) {
            this.finish();
        }
    }
    else if(sum==1){
        count++;
        if(count==5){
            this.finish();
        }
    }
    Toast.makeText(getApplicationContext(),"Rong Credentials",
            Toast.LENGTH_SHORT).show();
    Log.d("Dsdds",check.toString());
}

}

