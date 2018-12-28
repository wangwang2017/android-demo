// IBinderPool.aidl
package com.example.aidldemo;

// Declare any non-default types here with import statements

interface IBinderPool {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     * Binder连接池
     */

    IBinder queryBinder(int binderCode);
    //接着创建远程Service；
}
