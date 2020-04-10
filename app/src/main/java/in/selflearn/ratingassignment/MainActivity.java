package in.selflearn.ratingassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    Button Apply , history;
    Integer numstars=0;
    int min_rating=0 , max_rating=9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //finding the id of spinner
        spinner=findViewById(R.id.planets_spinner);

        //finding the id for the buttons.
        Apply=findViewById(R.id.Applybtn);
        history=findViewById(R.id.historybtn);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_name, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        Apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numstars!=0){
                Apply.setClickable(true);}

                //setting the minimum and maximum rating according to the user.
                max_rating=numstars;
                min_rating=1;

                Intent intent = new Intent(MainActivity.this,RatingActivity.class);
                intent.putExtra("min_rating",min_rating);
                intent.putExtra("max_rating",max_rating);
                startActivity(intent);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(MainActivity.this,History.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        numstars=  Integer.parseInt(String.valueOf(parent.getSelectedItem()));
        Apply.setText("Rating 1-"+String.valueOf(numstars));

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
