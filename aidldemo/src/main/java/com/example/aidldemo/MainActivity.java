package com.example.aidldemo;

import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private ISecurityCenter mSecurityCenter;
    private static final String TAG = "Client";
    private ICompute mCompute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      new Thread(new Runnable() {
          @Override
          public void run() {
              doWork();
          }
      }).start();

    }

    private void doWork(){
        BinderPool binderPool = BinderPool.getInstance(MainActivity.this);
        IBinder securityBinder = binderPool.queryBinder(BinderPool.BINDER_SECURITY_CENTER);
        mSecurityCenter = (ISecurityCenter)SecurityCenterImpl.asInterface(securityBinder);
        Log.d(TAG,"visit ISecurityBinder");
        String msg = "helloworld-android";
        System.out.println("content:"+msg);

        try {
            String password = mSecurityCenter.encrypt(msg);
            System.out.println("encrypt:"+password);
            String decryptStr = mSecurityCenter.decrypt(password);
            System.out.println("decrypt:"+decryptStr);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        Log.d(TAG,"visit ICompute");
        IBinder computeBinder = binderPool.queryBinder(BinderPool.BINDER_COMPUTE);
        mCompute = ComputeImpl.asInterface(computeBinder);

        try {
            System.out.println("3+5 = "+mCompute.add(3,5));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
