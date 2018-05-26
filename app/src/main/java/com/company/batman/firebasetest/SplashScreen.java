package com.company.batman.firebasetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class SplashScreen extends AppCompatActivity {
   ProgressBar progressBar;
    InterstitialAd interstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        progressBar=findViewById(R.id.progressBar3);
        interstitialAd=new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-7936771186810885/9668040765");
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
            @Override
            public void onAdOpened(){
                Toast.makeText(getApplicationContext(),"Sorry for the ads :( we have to maintain our server",Toast.LENGTH_LONG).show();
            }

        });

        Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(5000);
                    SplashScreen splashScreen=SplashScreen.this;
                    splashScreen.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(interstitialAd.isLoaded())
                            {
                                interstitialAd.show();
                            }
                            else {
                                requestNewInterstitial();
                            }
                        }
                    });

                }
                catch (Exception e)
                {

                }
            }
        };
        thread.start();
    }

    private void requestNewInterstitial() {
        Intent i=new Intent(getApplicationContext(),Main2Activity.class);
        startActivity(i);

    }


}
