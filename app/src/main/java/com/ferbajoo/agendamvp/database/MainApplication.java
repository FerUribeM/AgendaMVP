package com.ferbajoo.agendamvp.database;

import android.app.Application;

/**
 * Created by
 * feuribe on 09/02/2018.
 */

public class MainApplication extends Application {

    public static final String TAG = MainApplication.class.getSimpleName();
    public static Database mDb;

    @Override
    public void onCreate() {
        super.onCreate();
        mDb = new Database(this);
        mDb.open();
    }

    @Override
    public void onTerminate() {
        mDb.close();
        super.onTerminate();
    }
}
