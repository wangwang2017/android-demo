// IOnNewBookArrivedListener.aidl
package com.example.administrator.designmodel;

// Declare any non-default types here with import statements

import com.example.administrator.designmodel.Book;

interface IOnNewBookArrivedListener {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
   void onNewBookArrived(in Book newBook);
}
