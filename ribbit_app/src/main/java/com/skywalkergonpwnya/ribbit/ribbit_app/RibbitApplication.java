package com.skywalkergonpwnya.ribbit.ribbit_app;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;


public class RibbitApplication extends Application {
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "92QPF4URqbBEV6zIOqBFmUWeq6zYXSclgsv9Urph", "Oj6OxuRxyHnJ8NT62j26ui70nw3wPWAPVmgj8GgQ");


    }
}
