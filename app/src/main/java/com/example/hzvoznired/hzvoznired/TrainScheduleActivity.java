package com.example.hzvoznired.hzvoznired;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.example.hzvoznired.hzvoznired.db.DatabaseHelper;
import com.example.hzvoznired.hzvoznired.utils.UrlCreator;

import java.util.ArrayList;
import java.util.Locale;
import java.util.TreeMap;


public class TrainScheduleActivity extends Activity {

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private Spinner hzFromSpinner;
    private Spinner hzToSpinner;
    private DatePicker datePicker;


    private TreeMap<String, String> mAllHZDestinations;
    private static ArrayList<String> itemList;
    private String mFromCityCode;
    private String mToCityCode;
    private String mFromCityName;
    private String mToCityName;

    private Integer pYear;
    private Integer pMonth;
    private Integer pDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_schedule);

        mDbHelper = DatabaseHelper.getInstance(this);
        mDb = mDbHelper.getWritableDatabase();

        hzFromSpinner=(Spinner) findViewById(R.id.fromSpinner);
        hzToSpinner=(Spinner) findViewById(R.id.toSpinner);
        datePicker = (DatePicker) findViewById(R.id.datePicker);

        mAllHZDestinations = mDbHelper.getAllDestinations(mDb);
        setFromSpinnerValues(mAllHZDestinations);
        setToSpinnerValues(mAllHZDestinations);

        hzFromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                mFromCityName = hzFromSpinner.getSelectedItem().toString();
                mFromCityCode = getCityCode(hzFromSpinner, pos);
                Log.i("---- FROM CODE: ", mFromCityCode);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        hzToSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                mToCityName = hzToSpinner.getSelectedItem().toString();
                mToCityCode = getCityCode(hzToSpinner, pos);
                Log.i("---- TO CODE: ", mToCityCode);
                Log.i("---- DATE: ", dateString());

            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vozni_red, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    public void setFromSpinnerValues(TreeMap<String, String> h){
        itemList = new ArrayList<>();
        for (String city : mAllHZDestinations.keySet()) {
            itemList.add(city);
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, itemList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hzFromSpinner.setAdapter(spinnerAdapter);
    }


    public void setToSpinnerValues(TreeMap<String, String> h){
        itemList = new ArrayList<>();
        for (String city : mAllHZDestinations.keySet()) {
            itemList.add(city);
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, itemList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hzToSpinner.setAdapter(spinnerAdapter);
    }

    public String getCityCode(Spinner s, int p){
        //get clicked item and return city code
        return mDbHelper.getCityCodeFromCityName(mDb, s.getItemAtPosition(p).toString());
    }



    public String dateString(){
        //2016-09-06
        String d=null;
        pDay=datePicker.getDayOfMonth();
        pMonth=datePicker.getMonth()+1;
        pYear=datePicker.getYear();
        d=pYear.toString()+"-"+
                String.format(new Locale("hr_HR") ,"%02d", pMonth)+"-"+
                String.format(new Locale("hr_HR") ,"%02d", pDay);
        return d;

    }


    public void searchForResult(View view){
        Intent i = new Intent(getApplicationContext(), TrainScheduleResultActivity.class);

        //for now we are using hard-coded values for passanger number, passanger benefits,
        // directTrain=true, returnTrip=False and class=2
        String urlForSearch = UrlCreator.trainScheduleUrl(
                mFromCityCode,
                mToCityCode,
                dateString(),
                "True",
                "2",
                "False", "1", "11");

        i.putExtra("URL", urlForSearch);
        i.putExtra("FROM", mFromCityName);
        i.putExtra("TO", mToCityName);
        startActivity(i);
    }


}
