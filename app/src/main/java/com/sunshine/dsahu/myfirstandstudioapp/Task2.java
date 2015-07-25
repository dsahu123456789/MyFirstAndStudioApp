package com.sunshine.dsahu.myfirstandstudioapp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
                    //call method to wake up after 10 sec
                    wakeUp();
                    mDevicePolicyManager.setMaximumTimeToLock(mComponentName, 10000);
                    mDevicePolicyManager.lockNow();
                }else{

                    Toast.makeText(getApplicationContext(), "Not Registered as admin", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void wakeUp() {

// Create a new PendingIntent and add it to the AlarmManager
    Intent intent = new Intent(this, Task2.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    PendingIntent pendingIntent = PendingIntent.getActivity(this, 12345, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    AlarmManager am = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);
    /**
    * * if you want start your application only one time un-comment below
    * * line code and comment next line code */
    // am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
    am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 15, pendingIntent);

    KeyguardManager km = (KeyguardManager) getApplicationContext() .getSystemService(Context.KEYGUARD_SERVICE);
    final KeyguardManager.KeyguardLock kl = km .newKeyguardLock("MyKeyguardLock");
    kl.disableKeyguard();
    PowerManager pm = (PowerManager) getApplicationContext() .getSystemService(Context.POWER_SERVICE);
    PowerManager.WakeLock wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK
            | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "MyWakeLock");
    wakeLock.acquire();

    }


    @Override
    protected void onResume() {
        super.onResume();
        /******block is needed to raise the application if the lock is*********/
        Window mWind;
        mWind = this.getWindow();
        mWind.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        mWind.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        mWind.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
    /* ^^^^^^^block is needed to raise the application if the lock is*/
    }


    /**
     *  Check for request successful for register app as admin
     *
     */
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
