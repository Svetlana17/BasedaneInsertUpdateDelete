package com.example.user.catgreendao;

import android.app.Application;

import com.example.user.catgreendao.bd.DaoMaster;
import com.example.user.catgreendao.bd.DaoSession;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;

public class CatApp extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper devOpenHelper= new DaoMaster.DevOpenHelper(this, "cat.db");
        Database database = devOpenHelper.getWritableDb();
        daoSession = new DaoMaster(database).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
