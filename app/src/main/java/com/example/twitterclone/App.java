package com.example.twitterclone;

import android.app.Application;

import com.parse.Parse;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
        .applicationId("ByzEY4TCOOySytZHU91APwDhZpqQ9M5SoziKLMHw")
        .clientKey("gviqMrUFdnQuarPtK0OmBDVZXTAxODkHobd7I5w6")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
