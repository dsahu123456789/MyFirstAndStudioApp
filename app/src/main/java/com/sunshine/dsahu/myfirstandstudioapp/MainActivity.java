package com.sunshine.dsahu.myfirstandstudioapp;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    Button bTask1,bTask2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize
        init();
    }

    /* initialize */
    private void init() {
        bTask1 = (Button)findViewById(R.id.button_mainactivity_task1);
        bTask2 = (Button)findViewById(R.id.button_mainactivity_task2);

        bTask1.setOnClickListener(this);
        bTask2.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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



    @Override
    public void onClick(View v) {
       /*
         on button1 click textview1 will display "button 1 pressed"
                            textview2 will display ""
         on button2 click --vice-versa
        */


        switch (v.getId()){
            case R.id.button_mainactivity_task1:
                startActivity(new Intent(MainActivity.this, Task1.class));
                break;

            case R.id.button_mainactivity_task2:
                startActivity(new Intent(MainActivity.this, Task2.class));
                break;
        }
    }
}
