package com.example.hzvoznired.hzvoznired;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class SelectServiceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_service);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select, menu);
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


    public void whereIsTrain(View view){
        Intent intent = new Intent(this, WhereIsTrain.class);
        SelectServiceActivity.this.startActivity(intent);
    }




    public void gdjeJeVlakMethod(View view){
        Intent intent = new Intent(this, MainHzActivity.class);
        SelectServiceActivity.this.startActivity(intent);
    }



    public void trainStations(View view){
        AlertDialog alertDialog = new AlertDialog.Builder(SelectServiceActivity.this).create();
        alertDialog.setTitle("oups!!!");
        alertDialog.setMessage("This is not implemented yet");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
