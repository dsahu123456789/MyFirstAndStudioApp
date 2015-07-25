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

/*
 *  This Activity contains two two Button- Task1 and Task2
*/
public class MainActivity extends ActionBarActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize
        init();
    }

    /* initialize */
    private void init() {
        Button bTask1 = (Button)findViewById(R.id.button_mainactivity_task1);
        Button bTask2 = (Button)findViewById(R.id.button_mainactivity_task2);

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


      /*
         on button1 click textview1 will display "button 1 pressed"
          " button2  "    textview2 will display ""
         and --vice-versa
      */
    @Override
    public void onClick(View v) {

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
