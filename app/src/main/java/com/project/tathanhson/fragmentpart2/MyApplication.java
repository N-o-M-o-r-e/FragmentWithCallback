package com.project.tathanhson.fragmentpart2;

import android.app.Application;

public class MyApplication extends Application {
    private static MyApplication INSTANCE;
    private Storage storage;

    public static MyApplication getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        storage = new Storage();

    }

    public Storage getStorage() {
        return storage;
    }
}
