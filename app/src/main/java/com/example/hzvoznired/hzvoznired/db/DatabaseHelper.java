package com.example.hzvoznired.hzvoznired.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.text.Collator;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by etomlip on 25.8.2015..
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    private static DatabaseHelper mInstance = null;
    private static Context mCxt;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "hzDb";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INT";

    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " +
                    FeedReaderContract.FeedEntry.TABLE_NAME +
                    " (" +
                    FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    FeedReaderContract.FeedEntry.CITY_NAME + TEXT_TYPE + COMMA_SEP +
                    FeedReaderContract.FeedEntry.CITY_CODE + TEXT_TYPE + COMMA_SEP+
                    FeedReaderContract.FeedEntry.GDJE_JE_VLAK_FAV + TEXT_TYPE + COMMA_SEP+
                    FeedReaderContract.FeedEntry.PO_KOLODVORIMA_FAV + TEXT_TYPE + COMMA_SEP+
                    FeedReaderContract.FeedEntry.VOZNI_RED_FAV + TEXT_TYPE +
                    " )";




    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mCxt=context;
    }

    public static DatabaseHelper getInstance(Context ctx) {     //getting/creating singleton for database helper
        if (mInstance == null) {
            mInstance = new DatabaseHelper(ctx.getApplicationContext());
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createHZDestinationsTable(db);
        createHZFavouriteDestinationsTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void printAllDbEntries(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+FeedReaderContract.FeedEntry.TABLE_NAME, null);

        if (cursor .moveToFirst()) {

            while (!cursor.isAfterLast()) {
                String name = cursor.getString(cursor
                        .getColumnIndex(FeedReaderContract.FeedEntry.CITY_NAME));

                Log.i("---- DATA",name);
                cursor.moveToNext();
            }
        }

        cursor.close();
    }


    //CREATING TABLES METHODS
    private void createHZDestinationsTable(SQLiteDatabase db){
        String createHZLocationsTable =
                "CREATE TABLE " +
                        HZDestinationList.TABLE_NAME +
                        " (" +
                        HZDestinationList._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        HZDestinationList.CITY_NAME + TEXT_TYPE + COMMA_SEP +
                        HZDestinationList.CITY_CODE + TEXT_TYPE +
                        " )";

        db.execSQL(createHZLocationsTable);
    }

    private void createHZFavouriteDestinationsTable(SQLiteDatabase db){
        String createHZFavouriteDestinationsTable =
                "CREATE TABLE " +
                        HZScheduleFavorites.TABLE_NAME +
                        " (" +
                        HZScheduleFavorites._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        HZScheduleFavorites.CITY_NAME1 + TEXT_TYPE + COMMA_SEP +
                        HZScheduleFavorites.CITY_CODE1 + TEXT_TYPE + COMMA_SEP +
                        HZScheduleFavorites.CITY_NAME2 + TEXT_TYPE + COMMA_SEP +
                        HZScheduleFavorites.CITY_CODE2 + TEXT_TYPE +
                        " )";

        db.execSQL(createHZFavouriteDestinationsTable);

    }



    //INSERTING DATA INTO TABLES
    public boolean insertDestinationInTable(SQLiteDatabase db, String destinationCode, String
            destinationName){
        boolean data = false;

        ContentValues cv = new ContentValues();
        cv.put(HZDestinationList.CITY_CODE, destinationCode);
        cv.put(HZDestinationList.CITY_NAME, destinationName);

        long rowInserted = db.insert(HZDestinationList.TABLE_NAME,
                null,
                cv);
        if(rowInserted != -1){
            data = true;
        }
        return data;
    }


    public boolean insertFavouriteDestinationsInTable(SQLiteDatabase db, String fromCode,
                                                    String fromName, String toCode,
                                                       String toName){
        boolean data = false;
        ContentValues cv = new ContentValues();
        cv.put(HZScheduleFavorites.CITY_CODE1, fromCode);
        cv.put(HZScheduleFavorites.CITY_NAME1, fromName);
        cv.put(HZScheduleFavorites.CITY_CODE2, toCode);
        cv.put(HZScheduleFavorites.CITY_NAME2, toName);

        long rowInserted = db.insert(HZScheduleFavorites.TABLE_NAME,
                null,
                cv);

        if(rowInserted != -1){
            data = true;
        }
        return data;
    }



    public TreeMap<String, String> getAllDestinations(SQLiteDatabase db){

        Collator hrCollator = Collator.getInstance(new Locale("hr_HR"));
        hrCollator.setStrength(Collator.PRIMARY);
        TreeMap<String, String> data = new TreeMap<>(hrCollator);

        Cursor c = db.query(false,
                HZDestinationList.TABLE_NAME,   //table name
                new String[] {HZDestinationList.CITY_NAME, HZDestinationList.CITY_CODE},    //columns
                null,           //selections
                null,           //selection args
                null,           //group by
                null,           //having
                HZDestinationList.CITY_NAME + " ASC",           //order by
                null); //limit
        if (c!=null) {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                data.put(c.getString(0), c.getString(1));
                c.moveToNext();
            }
            c.close();
        }
        return data;
    }



    //CHECK IS TABLE EMPTY
    public boolean isDestinationsTableEmpty(SQLiteDatabase db){
        String count = "SELECT count(*) FROM " + HZDestinationList.TABLE_NAME;
        Cursor cursor = db.rawQuery(count, null);
        cursor.moveToFirst();
        int icount = cursor.getInt(0);
        cursor.close();
        if(icount>0){
            Log.i("---- COUNTED DEST : ", String.valueOf(icount));
            return false;
        }else{
            return true;
        }
    }


    //GET CITY CODE BASED ON CITY NAME
    public String getCityCodeFromCityName(SQLiteDatabase db, String cityName){
        String data = null;

        Cursor c = db.query(true,               //distinct (yes)
                HZDestinationList.TABLE_NAME,   //table name
                new String[] {HZDestinationList.CITY_CODE},    //columns
                HZDestinationList.CITY_NAME + "=?",     //selections (WHERE statement)
                new String []{cityName},                    //selection args
                null,           //group by
                null,           //having
                null,           //order by
                null);          //limit

        //read data
        if (c!=null) {
            c.moveToFirst();
            data = c.getString(0);
            c.close();
        }
        return data;
    }



    //DATA
    private static abstract class HZDestinationList implements BaseColumns {
        public static final String TABLE_NAME = "HZ_CITIES";
        public static final String CITY_NAME = "CITY_NAME";
        public static final String CITY_CODE = "CITY_CODE";
    }

    private static abstract class HZScheduleFavorites implements BaseColumns {
        public static final String TABLE_NAME = "HZ_SCHEDULE_FAVORITES";
        public static final String CITY_NAME1 = "CITY_NAME1";
        public static final String CITY_CODE1 = "CITY_CODE1";
        public static final String CITY_NAME2 = "CITY_NAME2";
        public static final String CITY_CODE2 = "CITY_CODE2";
    }
}


