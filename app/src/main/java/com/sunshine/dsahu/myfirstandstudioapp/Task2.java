package com.sunshine.dsahu.myfirstandstudioapp;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Task2 activity will lock the screen for 10 sec and unlock it for 10 sec and cycle goes on.
 */
public class Task2 extends ActionBarActivity implements View.OnClickListener {


    private static final int ADMIN_INTENT = 15;
    private static final String description = "Some Description About Your Admin";
    private DevicePolicyManager mDevicePolicyManager;
    private ComponentName mComponentName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task2);

        mDevicePolicyManager = (DevicePolicyManager)getSystemService(
                Context.DEVICE_POLICY_SERVICE);
        mComponentName = new ComponentName(this, MyAdmin.class);


        Button btnEnableAdmin = (Button) findViewById(R.id.btnEnableAdmin);
        Button btnDisableAdmin = (Button) findViewById(R.id.btnDisableAdmin);
        Button btnLock = (Button) findViewById(R.id.btnLock);
        btnEnableAdmin.setOnClickListener(this);
        btnDisableAdmin.setOnClickListener(this);
        btnLock.setOnClickListener(this);


    }

    /**
     * on button enable will register this app as admin
     * and button will disable the above
     * and button lock if as admin will lock the screen if as admin registered
     */
    @Override
    public void onClick(View v) {
        //TODO: lock button to be changed - set timer to lock and unlock after every 10 sec
        switch (v.getId()) {
            case R.id.btnEnableAdmin:
                Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
                intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mComponentName);
                intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,description);
                startActivityForResult(intent, ADMIN_INTENT);
                break;

            case R.id.btnDisableAdmin:
                mDevicePolicyManager.removeActiveAdmin(mComponentName);
                Toast.makeText(getApplicationContext(), "Admin registration removed", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnLock:
                boolean isAdmin = mDevicePolicyManager.isAdminActive(mComponentName);
                if (isAdmin) {
                    mDevicePolicyManager.setMaximumTimeToLock(mComponentName, 10000);
                    mDevicePolicyManager.lockNow();
                }else{

                    Toast.makeText(getApplicationContext(), "Not Registered as admin", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADMIN_INTENT) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Registered As Admin", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "Failed to register as Admin", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
