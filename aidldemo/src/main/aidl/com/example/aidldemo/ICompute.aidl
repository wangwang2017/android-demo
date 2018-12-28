// IComputer.aidl
package com.example.aidldemo;

// Declare any non-default types here with import statements

interface ICompute {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     * 计算加法的功能
     */
   int add(int a,int b);
}
