package com.zemosolabs.myplaces;

/**
 * Created by admin on 27/3/15.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends Activity {


    private final int SPLASH_DISPLAY_LENGTH = 1000;


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                startActivity(new Intent(Splash.this,MainActivity.class));
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}