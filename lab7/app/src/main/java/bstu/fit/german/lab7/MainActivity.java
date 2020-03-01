package bstu.fit.german.lab7;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.webkit.*;

public class MainActivity extends Activity {

    ListView listView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        listView = (ListView) findViewById(R.id.list);

        // Defined Array values to show in ListView
        String[] valuesq = new String[] {"Belgium",
                "Canada",
                "Denmark",
                "England",
                "Germany",
                "Ireland",
                "Korea South",
                "Netherlands"
        };


        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, valuesq);

        LayoutAnimationController controller = AnimationUtils
                .loadLayoutAnimation(this, R.anim.list_layout_controller);
        listView.setLayoutAnimation(controller);

        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        //listView.setOnItemClickListener(new OnItemClickListener() {

        listView.setOnItemClickListener(new OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                // ListView Clicked item index
                int itemPosition     = arg2; //position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(arg2/*position*/);

                switch(itemPosition)
                {
                    case 0:
                        itemValue="Belgium";
                        intent(itemValue);
                        break;

                    case 1:
                        itemValue="Canada";
                        intent(itemValue);
                        break;

                    case 2:
                        itemValue="Denmark";

                        intent(itemValue);
                        break;
                    case 3:
                        itemValue="England";

                        intent(itemValue);
                        break;
                    case 4:
                        itemValue="Germany";

                        intent(itemValue);
                        break;
                    case 5:
                        itemValue="Ireland";
                        intent(itemValue);
                        break;
                    case 6:
                        itemValue="Korea South";
                        intent(itemValue);
                        break;
                    case 7:
                        itemValue="Netherlands";
                        intent(itemValue);
                        break;

                }
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();
            }

        });
    }
private void intent(String itemValue){
    Intent intent = new Intent(MainActivity.this, CitiesActivity.class);
    intent.putExtra("city", itemValue);
    startActivity(intent);
}
}


