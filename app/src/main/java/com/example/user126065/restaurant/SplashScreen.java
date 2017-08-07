package com.example.user126065.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
public class SplashScreen extends AppCompatActivity {
// Splash screen timer
        private static int TIME_OUT = 6000;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_splash_screen);

            new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity
                    Intent i = new Intent(SplashScreen.this,LoginActivity.class);
                    startActivity(i);

                    // Close this activity
                    finish();
                }
            }, TIME_OUT);
        }
    }

