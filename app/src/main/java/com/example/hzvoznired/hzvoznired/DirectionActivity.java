package com.example.hzvoznired.hzvoznired;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class DirectionActivity extends Activity {
    private Intent intentForReceivedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction);
        //set cityData
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_direction, menu);
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


    public String[] getCityData(){
        intentForReceivedData=getIntent();
        String[] cityD = intentForReceivedData.getStringArrayExtra("cityData");
        Log.i("Rthru intent: ",cityD[0]);
        Log.i("Rthru intent: ",cityD[1]);
        return cityD;
    }

    public String getCityName(){
        return getCityData()[0];
    }

    public String getCityCode(){
        return getCityData()[1];
    }

    //onClick
    public void getArrivalData(View view){
        String linkData[] = createLinkData(getCityCode(), "D", getCityName());
        Intent i = new Intent(this, FinalDataActivity.class);
        i.putExtra("linkdata", linkData);
        DirectionActivity.this.startActivity(i);
        Log.i("LINK : ", linkData[0]);
        Log.i("LINK : ", linkData[1]);
        Log.i("LINK : ", linkData[2]);
    }

    //onClick
    public void getDepartureData(View view){
        String linkData[] = createLinkData(getCityCode(), "O", getCityName());
        Intent i = new Intent(this, FinalDataActivity.class);
        i.putExtra("linkdata", linkData);
        DirectionActivity.this.startActivity(i);
        Log.i("LINK : ", linkData[0]);
        Log.i("LINK : ", linkData[1]);
        Log.i("LINK : ", linkData[2]);
    }




    public String[] createLinkData(String city, String direction, String cityName){
        String l[] ={city,direction,cityName};
        return l;
    }

}
