package com.company.batman.firebasetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminAuth extends AppCompatActivity {
EditText pin;
Button go;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_auth);
    go=findViewById(R.id.button2);
    pin=findViewById(R.id.pin);
    go.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
         /*   if(pin.getText().toString().equals("")){  //it's our secret code :)
                Intent i=new Intent(AdminAuth.this,MainActivity.class);
                startActivity(i);
            }
            else {
                Toast.makeText(getApplicationContext(),"Please don't try.....You're not our admin :(",Toast.LENGTH_LONG).show();
            }*/
        }
    });

    }
}
