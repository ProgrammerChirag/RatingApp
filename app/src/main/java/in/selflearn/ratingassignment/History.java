package in.selflearn.ratingassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {

    TextView textView;
    String[] rating ;
    String[] date;
    String[] time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);




        textView=findViewById(R.id.text_view);
        DataBase dataBase = new DataBase(this);
        List<RatingData> list = dataBase.getRating();

        rating= new String[list.size()];
        date=new String[list.size()];
        time=new String[list.size()];

        for(int i=0;i<list.size();i++){
            rating[i]= String.valueOf(list.get(i).getRating());
            date[i]=String.valueOf(list.get(i).getDate());
            time[i]=String.valueOf(list.get(i).getTime());
        }

        textView.setText("");
        for(int i=0;i<list.size();i++){

            textView.append("rating:"+rating[i]+"  Date:  "+date[i]+"  Time: "+time[i]);
            textView.append("\n");

        }

    }
}
