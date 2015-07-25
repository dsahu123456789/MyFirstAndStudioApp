package com.sunshine.dsahu.myfirstandstudioapp;

import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*
 *  This activity uses two fragment
 *  First fragments(upFragment) contain two button and onclick\
 *  responds to textView of second fragment(downFragment)
 */

public class Task1 extends ActionBarActivity implements View.OnClickListener {

    private TextView mTextView1,mTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task1);

        //initialize method
        init();
    }

    /* initialize all the variable here*/
    private void init() {
        Button mButton1 = (Button)findViewById(R.id.button1);
        Button mButton2 = (Button)findViewById(R.id.button2);
        mTextView1 = (TextView)findViewById(R.id.textView1);
        mTextView2 = (TextView)findViewById(R.id.textView2);

        // register button click event
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    /*
     *   setting up fragment in Activity
     */
    @Override
    protected void onResume() {
        super.onResume();
        UpFragment upFragment = (UpFragment)getFragmentManager().findFragmentById(R.id.up_fragment);
        DownFragment downFragment = (DownFragment)getFragmentManager()
                .findFragmentById(R.id.down_fragment);
    }


    /*
    *    on button1 click textview1 will display "button 1 pressed"
    *                      textview2 will display ""
    *    on button2 click --vice-versa
    */
    @Override
    public void onClick(View v) {
         switch (v.getId()){
            case R.id.button1:
                mTextView1.setText("Button 1 pressed");
                mTextView2.setText("");
                break;

            case R.id.button2:
                mTextView1.setText("");
                mTextView2.setText("Button 2 pressed");
                break;
        }
    }
}
