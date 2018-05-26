package com.company.batman.firebasetest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
EditText title,descc;
ImageView preview;
Bitmap bm;

public DatabaseReference databaseReference;
public StorageReference storageReference;
public Uri uri;
    Button browse,upload;
    public static final int GALLERY_INTENT=2;

    public static final String FB_STORAGE_PATH = "image/";
    public static final String FB_DATABASE_PATH = "image";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title=(EditText)findViewById(R.id.title1);
        preview=(ImageView)findViewById(R.id.preview);
        descc=findViewById(R.id.descr);
        browse=(Button)findViewById(R.id.browse);
        databaseReference= FirebaseDatabase.getInstance().getReference().child(FB_DATABASE_PATH);
        storageReference= FirebaseStorage.getInstance().getReference();
        upload=(Button)findViewById(R.id.upload);
        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery=new Intent(Intent.ACTION_PICK);
                gallery.setType("image/*");
                startActivityForResult(gallery,GALLERY_INTENT);
            }
        });
       upload.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

            if (uri!=null)
            {
                Toast.makeText(getApplicationContext(),"uploading",Toast.LENGTH_LONG).show();

                StorageReference reference=storageReference.child("pic").child(uri.getLastPathSegment());
                reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getApplicationContext(),"done",Toast.LENGTH_LONG).show();
                        ImageTask imageTask=new ImageTask(title.getText().toString(),taskSnapshot.getDownloadUrl().toString(),descc.getText().toString());
                        String id=databaseReference.push().getKey();
                        databaseReference.child(id).setValue(imageTask);
                        Intent xi=new Intent(getApplicationContext(),Main2Activity.class);
                        startActivity(xi);



                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Upload error",Toast.LENGTH_LONG).show();
                    }
                });





            }
           }
       });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==GALLERY_INTENT&&resultCode==RESULT_OK)
        {
           uri=data.getData();
            try {
                bm= MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                preview.setImageBitmap(bm);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        super.onActivityResult(requestCode, resultCode, data);
    }



}
