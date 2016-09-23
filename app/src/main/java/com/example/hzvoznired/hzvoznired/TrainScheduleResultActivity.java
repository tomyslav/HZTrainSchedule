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


public class TrainScheduleResultActivity extends Activity {
    private Intent i;
    String f=null;
    String t=null;
    String mUrl;
    private Document doc;
    private Element content = null;
    private ArrayList<ArrayList<String>> tableInArray;

    private TextView mTextViewCities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_schedule_result);
        mTextViewCities = (TextView) findViewById(R.id.tv_cities);

        mTextViewCities.setText(fromCityText()+" - "+toCityText());

        //get URL from previous intent
        getLink();

        Log.i("---- URL: ", mUrl);
        new GetTrainSchedule().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vozni_red_data, menu);
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


    public void getLink(){
        i = getIntent();
        mUrl=i.getStringExtra("URL");
    }

    public String fromCityText(){
        i = getIntent();
        return i.getStringExtra("FROM");
    }

    public String toCityText(){
        i = getIntent();
        return i.getStringExtra("TO");
    }


    private class GetTrainSchedule extends AsyncTask<Void, Void, Void> {

        private ProgressDialog dialog = new ProgressDialog(TrainScheduleResultActivity.this);
        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Getting information");
            this.dialog.show();
        }


        @Override
        protected Void doInBackground(Void... params) {
            connectToHzNet(mUrl);
            return null;
        }



        public void connectToHzNet(String linkToConnect){
            try {
                Log.i("----connectToHzNet ", "started");
                doc = Jsoup.connect(linkToConnect).get();
                Log.i("alldata: ", doc.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            content = doc.select("table.outerTable").first();

            Log.i("----Content ", content.toString());


            //remove dialog when data is fetched
            if (dialog.isShowing()) {
                dialog.dismiss();
            }

            //organize data
            if (content!=null) {
                Log.i("----onPostExecute ", "started");
                tableInArray = createTableInArray(content);
                populateTableWithData(tableInArray);
                Log.i("----onPostExecute ", " done");
            }else{
                Log.i("----onPostExecute", "---failure");
            }
        }
    }


    public ArrayList<ArrayList<String>> createTableInArray(Element table){
        Elements rows = table.select("tr");
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        for (Element row : rows){
            if (!row.toString().contains("Preloader.GIF")){
                Elements items = row.select("td");
                ArrayList<String> temp = new ArrayList<>();
                for (Element item : items){
                    temp.add(item.text());
                }
                data.add(temp);
                Log.i("-----",temp.toString());
            }
        }
        return  data;
    }

    public void populateTableWithData(ArrayList<ArrayList<String>> data){
        TableLayout table = (TableLayout)findViewById(R.id.tableForVozniRed);
        for(int i=0;i<data.size();i++){
            ArrayList<String> temp = data.get(i);

            Log.i("-----tempSize : ", String.valueOf(temp.size()));

            // create a new TableRow
            TableRow row = new TableRow(this);
            for(int j=0;j<temp.size();j++){

                Log.i("-----TD : ", temp.get(j));

                if (j==1||j==4||j==5||j==6||j==7){
                    //skipping???
                }else {
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
            }
            if (i%2==0) row.setBackgroundColor(0xFFDDDDDD);
            table.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }




}
