package com.example.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * 创建binder远程service
 *当binder连接池连接上远程服务时，会根据不同模块的标识即binderCode返回不同的Binder的对象
 * 通过这个Binder对象所执行的操作全部发生在远程服务端。
 */

public class BinderPoolService extends Service {

    private static final String TAG = "BinderPoolService";

    private Binder mBinderPool = new BinderPool.BinderPoolImpl();

    public BinderPoolService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"onBind");
        // TODO: Return the communication channel to the service.
        return mBinderPool;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
