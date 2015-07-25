package com.sunshine.dsahu.myfirstandstudioapp;

import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Task1 extends ActionBarActivity implements View.OnClickListener {

    Button button1,button2;
    TextView tv1,tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task1);

        //initialize
        init();
    }

    /* initialize */
    private void init() {
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        tv1 = (TextView)findViewById(R.id.textView1);
        tv2 = (TextView)findViewById(R.id.textView2);

        // register button click event
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
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
    protected void onResume() {
        super.onResume();
        /*
         * set up fragment in Activity
         */

        UpFragment upFragment = (UpFragment)getFragmentManager().findFragmentById(R.id.up_fragment);
        DownFragment downFragment = (DownFragment)getFragmentManager().findFragmentById(R.id.down_fragment);
    }



    @Override
    public void onClick(View v) {
       /*
         on button1 click textview1 will display "button 1 pressed"
                            textview2 will display ""
         on button2 click --vice-versa
        */


        switch (v.getId()){
            case R.id.button1:
                tv1.setText("Button 1 pressed");
                tv2.setText("");
                break;

            case R.id.button2:
                tv1.setText("");
                tv2.setText("Button 2 pressed");
                break;
        }
    }
}
