package in.selflearn.ratingassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DataBase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "RatingHist";
    private static final String TABLE_Rating = "Rating";
    private static final String KEY_ID = "id";
    private static final String KEY_DATE = "Date";
    private static final String KEY_RATING= "NUM_RATING";
    private static final String KEY_TIME="Time";

    public DataBase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_Rating + "("
                + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_DATE + " TEXT, "
                + KEY_RATING + " TEXT, " + KEY_TIME  + " TEXT" + ")";
        Log.d("dbexe",CREATE_CONTACTS_TABLE);
        try {
            db.execSQL(CREATE_CONTACTS_TABLE);
        }catch (Exception e){
            Log.d("exe",e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Rating);

        onCreate(db);
    }

    public boolean addRating(RatingData ratingData){

     try{

         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues contentValues=new ContentValues();
         contentValues.put(KEY_DATE,ratingData.getDate());
         contentValues.put(KEY_TIME,ratingData.getTime());
         contentValues.put(KEY_RATING,ratingData.getRating());

         Log.d("values", String.valueOf(contentValues));
         long message= db.insert(TABLE_Rating,null,contentValues);


         db.close();

     }catch (SQLException e){
         Log.d("exception",e.getMessage());
     }
        return true;
    }

    public List<RatingData> getRating(){

        SQLiteDatabase db = this.getReadableDatabase();

        List<RatingData> list = new ArrayList<>();

        String selectQuery = "SELECT * FROM "+TABLE_Rating;

        Cursor cursor=db.rawQuery(selectQuery,null);


        if(cursor.moveToFirst()){
            do{
                RatingData ratingData= new RatingData();
                ratingData.setDate(cursor.getString(1));
                ratingData.setRating(Double.parseDouble(cursor.getString(2)));
                ratingData.setTime(cursor.getString(3));

                list.add(ratingData);

            }while (cursor.moveToNext());
        }
        return  list;
    }

}
