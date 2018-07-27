package com.pqanh95.app.aibithnai;

import android.app.Application;

public class App extends Application {
    private int level = 0;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
