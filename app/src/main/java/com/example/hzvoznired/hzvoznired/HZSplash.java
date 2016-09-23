package com.example.hzvoznired.hzvoznired;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.hzvoznired.hzvoznired.db.DatabaseHelper;

import java.util.Map;
import java.util.TreeMap;

public class HZSplash extends AppCompatActivity {

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hzsplash);

        mDbHelper = DatabaseHelper.getInstance(this);
        mDb = mDbHelper.getWritableDatabase();


        //thread that is used for splash screen and for initial population of table with all
        // destinations
        Thread timer=new Thread(){
            public void run() {
                try {

                    if(mDbHelper.isDestinationsTableEmpty(mDb)){
                        TreeMap<String, String> destinations = new Cities().getAllCities();
                        for (Map.Entry<String, String> city : destinations.entrySet()){
                            mDbHelper.insertDestinationInTable(mDb, city.getKey(), city.getValue());
                            Log.i("---- SPLASH : ", city.getKey() + " - " + city.getValue());
                        }
                    }else{
                        sleep(2000);
                        mDbHelper.getAllDestinations(mDb);
                        Log.i("---- SPLASH : ", "TABLE IS ALREDY POPULATED");
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally
                {
                    Intent i=new Intent(HZSplash.this, TrainScheduleActivity.class);
                    finish();
                    startActivity(i);
                }
            }
        };
        timer.start();
    }
}
