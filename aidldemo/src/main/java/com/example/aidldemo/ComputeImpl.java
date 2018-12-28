package com.example.aidldemo;

import android.os.RemoteException;

/**
 * Created by Administrator on 2018/12/28/028.
 */

public class ComputeImpl extends ICompute.Stub {
    @Override
    public int add(int a, int b) throws RemoteException {
        return a+b;
    }
}
