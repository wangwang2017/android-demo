package com.example.administrator.designmodel;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import com.example.administrator.designmodel.IOnNewBookArrivedListener;
public class BookManagerService extends Service {

    private static final String TAG = "BMS";
//    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
//        @Override
//        public void binderDied() {
//            if (mBinder == null) return;
//            mBinder.unlinkToDeath(mDeathRecipient,0);
//              mBinder = null;
    // 重连代码

//        }
//    }; //binder 的重连
    private AtomicBoolean mIsServiceDestroyed = new AtomicBoolean(false);
    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<Book>();
    private RemoteCallbackList<IOnNewBookArrivedListener> mListenerList = new RemoteCallbackList<IOnNewBookArrivedListener>();
    private Binder mBinder = new IBookManager.Stub(){

        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
            mListenerList.register(listener);
        }

        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
            mListenerList.unregister(listener);
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            //返回false  可以禁止与客户端通讯  第二种
//            int check = checkCallingOrSelfPermission("permission");
//            if (check == PackageManager.PERMISSION_DENIED){
//                return false;
//            }
//            第三种给sevice指定permission属性
            return super.onTransact(code, data, reply, flags);
        }
    };



    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(1,"android"));
        mBookList.add(new Book(2,"java"));
        new Thread(new ServiceWorker()).start();
    }

    public BookManagerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

        //此处进行权限认证 第一种
//        int check = checkCallingOrSelfPermission("permission");
//        if (check == PackageManager.PERMISSION_DENIED){
//            return null;
//        }
        return mBinder;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mIsServiceDestroyed.set(true);
    }

    private class ServiceWorker implements Runnable {
        @Override
        public void run() {
            while(!mIsServiceDestroyed.get()){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int bookId = mBookList.size()+1;
                Book newBook = new Book(bookId,"new Book#"+bookId);

                try {
                    onNewBookArrived(newBook);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void onNewBookArrived(Book newBook) throws RemoteException{
        mBookList.add(newBook);
        final int N = mListenerList.beginBroadcast();
        for (int i = 0; i < N ; i++) {
            IOnNewBookArrivedListener listener = mListenerList.getBroadcastItem(i);
            try {
                if (listener != null){
                    listener.onNewBookArrived(newBook);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        mListenerList.finishBroadcast();
    }
}
