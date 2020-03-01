package bstu.fit.german.lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class CitiesActivity extends AppCompatActivity {
    ArrayList<String> belgium,canada,denmark,germany,england,ireland,korea,netherlands;
    ListView listView;
    private ImageView wv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        init();
        final Animation anim= AnimationUtils.loadAnimation(this, R.anim.rotate);
        Bundle arguments = getIntent().getExtras();
        String city = arguments.get("city").toString();

        switch(city){
            case "Belgium" :
                setList(belgium);
                wv1.setImageResource(R.drawable.image1);
                wv1.startAnimation(anim);
            break;
            case "Canada":
                setList(canada);
                wv1.setImageResource(R.drawable.image2);
                wv1.startAnimation(anim);
                break;
            case "Denmark":
                setList(denmark);
                wv1.setImageResource(R.drawable.image3);
                wv1.startAnimation(anim);
                break;
            case "England":
                setList(england);
                wv1.setImageResource(R.drawable.image4);
                wv1.startAnimation(anim);
                break;
            case "Germany":
                setList(germany);
                wv1.setImageResource(R.drawable.image5);
                wv1.startAnimation(anim);
                break;
            case "Ireland":
                setList(ireland);
                wv1.setImageResource(R.drawable.image6);
                wv1.startAnimation(anim);
                break;
            case "Korea South":
                setList(korea);
                wv1.setImageResource(R.drawable.image7);
                wv1.startAnimation(anim);
                break;
            case "Netherlans":
                setList(netherlands);
                wv1.setImageResource(R.drawable.image8);
                wv1.startAnimation(anim);
                break;
        }
    }
    private void setList(ArrayList<String> country){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, country);
        listView.setAdapter(adapter);
    }
    private void init(){
        canada=new ArrayList<String>();
        canada.add("Ontario");
        canada.add("Leduc");
        canada.add("Medicine Hat");
        canada.add("Spruce Grove");

        listView=findViewById(R.id.list2);
        wv1=(ImageView)findViewById(R.id.imageView1);

        belgium = new ArrayList<String>();
        belgium.add("Brussels");
        belgium.add("Bree");
        belgium.add("Brugs");
        belgium.add("Chimay");

        denmark=new ArrayList<String>();
        denmark.add("Kolding");
        denmark.add("Horsens");
        denmark.add("Skive");
        denmark.add("Varde");

        germany= new ArrayList<String>();
        germany.add("Berlin");
        germany.add("Frankfurt am Main");
        germany.add("Florstadt");
        germany.add("Fürth");

        england = new ArrayList<String>();
        england.add("London");
        england.add("Manchester");
        england.add("Liverpool");
        england.add("Brisol");

        ireland = new ArrayList<String>();
        ireland.add("Dublin");
        ireland.add("Cork");
        ireland.add("Swords");
        ireland.add("Dundalk");

        korea=new ArrayList<String>();
        korea.add("Seoul");
        korea.add("Busan");
        korea.add("Tegu");
        korea.add("Tedjon");

        netherlands=new ArrayList<String>();
        netherlands.add("Amsterdam");
        netherlands.add("Gaaga");
        netherlands.add("Utreht");
        netherlands.add("Gaupda");

    }
}
