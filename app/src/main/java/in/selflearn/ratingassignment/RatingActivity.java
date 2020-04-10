package in.selflearn.ratingassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import java.util.List;

public class RatingActivity extends AppCompatActivity {

    ViewPager viewPager;
    int min_star, max_star;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        viewPager=findViewById(R.id.viewpager);


        min_star=getIntent().getIntExtra("min_rating",1);
        max_star=getIntent().getIntExtra("max_rating",9);


        viewPager.setAdapter(new SlideAdapter(this,min_star,max_star));



    }
}
