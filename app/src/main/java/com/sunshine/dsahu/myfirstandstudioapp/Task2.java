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


public class Task2 extends ActionBarActivity implements View.OnClickListener {


    Button bLock;

//
//    DevicePolicyManager devicePolicyManager;
//    ActivityManager activityManager;
//    ComponentName componentName;

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


//
//        KeyguardManager mgr = (KeyguardManager)getSystemService(Activity.KEYGUARD_SERVICE);
//        KeyguardManager.KeyguardLock lock = mgr.newKeyguardLock(KEYGUARD_SERVICE);
//        lock.reenableKeyguard();
//        DevicePolicyManager mDPM;
//        ComponentName devAdminReceiver; // this would have been declared in your class body
// then in your onCreate
//        mDPM = (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);
//        devAdminReceiver = new ComponentName(this, MyAdmin.class);
//then in your onResume

//        boolean admin = mDPM.isAdminActive(devAdminReceiver);
//        if (admin)
//            mDPM.lockNow();
//        else Log.i("LOCkScreen asdfasdfasdfasdfafasdfasfasfafasfasfas","Not an admin");


//
//        Window wind;
//        /******block is needed to raise the application if the lock is*********/
//        wind = this.getWindow();
//        wind.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
//        wind.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
//        wind.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
//    /* ^^^^^^^block is needed to raise the application if the lock is*/

//        devicePolicyManager = (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);
//        activityManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
//        componentName = new ComponentName(this, MyAdmin.class);

//        WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);

        //Unlock
        //http://developer.android.com/reference/android/app/Activity.html#getWindow()
//        Window window = getWindow();
//        window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

        //Lock device
//        DevicePolicyManager mDPM;
//        mDPM = (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);

        bLock = (Button)findViewById(R.id.btnLock);
        bLock.setOnClickListener(this);

        // using powerManager wakelock
//        wakeLock();
    }

    private void wakeLock() {

//        PowerManager pm = (PowerManager)getSystemService(Context.POWER_SERVICE);
//        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"MyTag");
//
//        wl.acquire();
//
//        KeyguardManager keyguardManager = (KeyguardManager)getSystemService(Activity.KEYGUARD_SERVICE);
//        KeyguardManager.KeyguardLock lock = keyguardManager.newKeyguardLock(KEYGUARD_SERVICE);
//
//        lock.reenableKeyguard();

//        DevicePolicyManager mDPM;
//        ComponentName mDeviceAdminSample;
//
//        mDPM = (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);
//        mDeviceAdminSample = (ComponentName)getCallingActivity();
//
//        long timeMs = 1000L*100;
//        mDPM.setMaximumTimeToLock(mDeviceAdminSample, timeMs);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(10000);
//                }catch (InterruptedException e){
//                    e.printStackTrace();
//                }
//                //wl.release();
//            }
//        }).start();

    }


    @Override
    public void onClick(View v) {
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
