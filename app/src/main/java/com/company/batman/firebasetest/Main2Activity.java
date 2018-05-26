package com.company.batman.firebasetest;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
ImageView imageView;
TextView textView;
ArrayList<ImageTask> details;
DatabaseReference databaseReference;
RecyclerView recyclerView;
AdView adView,adView3;
InterstitialAd interstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_main2);
        if(!isInternetAvailable()){
            TextView textView11=(TextView)findViewById(R.id.internet);
            textView11.setVisibility(View.VISIBLE);
        }
        else {
        details=new ArrayList<>();
        MobileAds.initialize(this,"ca-app-pub-7936771186810885~3242904693");
        adView=(AdView)findViewById(R.id.adView);
        adView.loadAd(new AdRequest.Builder().build());
        recyclerView=(RecyclerView)findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseReference=FirebaseDatabase.getInstance().getReference().child(MainActivity.FB_DATABASE_PATH);
        databaseReference.addValueEventListener(new ValueEventListener() {
         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             for (DataSnapshot ds:dataSnapshot.getChildren()){
                 ImageTask imageTask=new ImageTask();
                 imageTask.setTitle(ds.getValue(ImageTask.class).getTitle());
                 imageTask.setDesc(ds.getValue(ImageTask.class).getDesc());
                 imageTask.setUrl(ds.getValue(ImageTask.class).getUrl());
                 details.add(imageTask);

             }
             MyAdapter myAdapter=new MyAdapter(details,getApplicationContext());
             recyclerView.setAdapter(myAdapter);

         }
         @Override
         public void onCancelled(DatabaseError databaseError) {
             Toast.makeText(getApplicationContext(),"Error occured",Toast.LENGTH_LONG).show();
         }
     });
    }
    }
public boolean isInternetAvailable()
{
    boolean connected = false;
    ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
    if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
            connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
        //we are connected to a network
        connected = true;
    }
    else
        connected = false;
    return connected;
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.admin);
        {
            Intent intent=new Intent(getApplicationContext(),AdminAuth.class);
            startActivity(intent);
        }



        return super.onOptionsItemSelected(item);
    }
}
