package com.example.hzvoznired.hzvoznired;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class FinalDataActivity extends Activity {
    private Intent intentForCityData;
    private String cityCode;
    private String direction;
    private String cityName;
    private TextView titleCity;
    private TextView titleDirection;
    private String link;
    private Document doc;
    private Element content = null;
    private ArrayList<ArrayList<String>> tableInArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_data);
        titleCity = (TextView) findViewById(R.id.cityName);
        titleDirection = (TextView) findViewById(R.id.cityDirection);

        this.setCityDataFromPreviousActivity();
        //set title
        titleCity.setText(cityName);
        titleDirection.setText(directionText(direction));

        //create link for scraping
        link= this.createLink(cityCode,direction);
        Log.i("final link: ", link);
        new DataGrabber().execute();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_final_data, menu);
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


    public void setCityDataFromPreviousActivity(){
        intentForCityData = getIntent();
        String[] cityData = intentForCityData.getStringArrayExtra("linkdata");
        cityCode=cityData[0];
        direction=cityData[1];
        cityName=cityData[2];
    }

    public String createLink(String city, String direction){
        String l ="http://www.hzpp.hr/CorvusTheme/TimetableOnline/Result?Location="+city+"&TravelDirection="+direction;
        return l;
    }

    public String geLink(){
        return link;
    }

    public String directionText(String d){
        String data = "ERROR";
        if (d.equals("D")) data="DOLAZAK";
        else if (d.equals("O")) data="ODLAZAK";
        return data;
    }




    public ArrayList<ArrayList<String>> createTableInArray(Element table){
        Elements rows = table.select("tr");
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        for (Element row : rows){
            if (row.toString().contains("Preloader.GIF")){
            }else{
                Elements items = row.select("td");
                ArrayList<String> temp = new ArrayList<String>();
                for (Element item : items){
                    temp.add(item.text().toString());
                }
                data.add(temp);
                Log.i("-----",temp.toString());
            }
        }
        return  data;
    }


    public void populatetableWithData(ArrayList<ArrayList<String>> data){
        TableLayout table = (TableLayout)findViewById(R.id.tableForAllData);
        for(int i=0;i<data.size();i++){
            ArrayList<String> temp = data.get(i);
            // create a new TableRow
            TableRow row = new TableRow(this);
            for(int j=0;j<temp.size();j++){
                // create a new TextView for showing xml data
                TextView t = new TextView(this);
                // set the text to "text xx"
                t.setText(temp.get(j));
                t.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
                t.setPadding(0, 5, 0, 5);
                // add the TextView  to the new TableRow
                row.addView(t);
                // add the TableRow to the TableLayout
            }
            if (i%2==0) row.setBackgroundColor(0xFFDDDDDD);
            table.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }



    private class DataGrabber extends AsyncTask<Void, Void, Void> {


        private ProgressDialog dialog = new ProgressDialog(FinalDataActivity.this);

        /** progress dialog to show user that the backup is processing. */
        /** application context. */
        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Please wait");
            this.dialog.show();
        }


        @Override
        protected Void doInBackground(Void... params) {
            connectToHzNet(link);
            return null;
        }

        public void connectToHzNet(String linkToConnect){
            try {
                Log.i("connect", "---done");
                doc = Jsoup.connect(linkToConnect)
                        .userAgent("Mozilla/5.0 (Linux; Android 4.0.4; Galaxy Nexus Build/IMM76B) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.133 Mobile Safari/535.19")
                        .timeout(10000)
                        .post();
                Log.i("alldata: ", doc.toString());
                } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            content = doc.select("table.outerTable").first();

            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            if (content!=null) {
                Log.i("connected", "---done");
                tableInArray = createTableInArray(content);
                populatetableWithData(tableInArray);
                Log.i("gettable", "---done");
            }else{
                Log.i("failure", "---failure");
            }
        }
    }//DataGrabber
}
