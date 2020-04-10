package in.selflearn.ratingassignment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.PagerAdapter;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SlideAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    private float[] list_Rating_Bar ;
    int min_star;
    int max_star;


    public SlideAdapter(Context context ,int min_star , int max_star){
        this.context=context;
        this.min_star=min_star;
        this.max_star=max_star;

        list_Rating_Bar=new float[max_star];
        for(int i =0;i<max_star;i++) {
            list_Rating_Bar[i]=min_star+i;
            Log.d("listforstar", String.valueOf(list_Rating_Bar[i]));
        }

    }

    @Override
    public int getCount() {
        return list_Rating_Bar.length;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, int position) {
        layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view= layoutInflater.inflate(R.layout.slide,container,false);
        LinearLayout layoutslides=view.findViewById(R.id.slidelayout);
        final RatingBar ratingBar = view.findViewById(R.id.ratingbar2);
        ratingBar.setNumStars(max_star);
        Button savebtn = view.findViewById(R.id.savebtn);
        ratingBar.setRating(Float.parseFloat(String.valueOf(Math.ceil(list_Rating_Bar[position]))));
        savebtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                LocalDate date = LocalDate.now();
                String cur_date = String.valueOf(date);

                LocalTime time = LocalTime.now();
                String cur_time = String.valueOf(time);

                Double rating = Double.parseDouble(String.valueOf(ratingBar.getRating()));

                RatingData ratingData = new RatingData(cur_date,cur_time,rating);

                DataBase dataBase = new DataBase(context);

                dataBase.addRating(ratingData);


                Intent intent = new Intent(context,RatingActivity.class);
                intent.putExtra("min_rating",min_star);
                intent.putExtra("max_rating",max_star);
                context.startActivity(intent);
                ((Activity)context).finish();


            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view==(LinearLayout)object);
    }
}
