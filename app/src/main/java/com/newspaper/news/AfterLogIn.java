package com.newspaper.news;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AfterLogIn extends AppCompatActivity
{
    TextView time,date;
    FirebaseStorage storage;
    StorageReference reference;
    ImageButton imageButton;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_after_log_in);
        //date=findViewById(R.id.idate);
        //time=findViewById(R.id.idtime);
        Calendar calendar=Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("hh:mm:ss");
        String val=simpleDateFormat.format(calendar.getTime());
       // date.setText(currentDate);
        //time.setText(val);
        init();
        Imageinit();



    }

    private void init()
    {
        imageButton=findViewById(R.id.idimage);
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Data is being Loaded");
        progressDialog.show();
    }

    private void Imageinit()
    {
        storage= FirebaseStorage.getInstance();
        reference=storage.getReferenceFromUrl("gs://news-896cd.appspot.com");
        StorageReference fisrt=reference.child("afterlogin.jpeg");
        File myfile=null;
        try
        {
            myfile=File.createTempFile("images",".jpg");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        final File finalmyfile1=myfile;
        fisrt.getFile(myfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot)
            {
                Bitmap bitmap= BitmapFactory.decodeFile(finalmyfile1.getAbsolutePath());
                imageButton.setImageBitmap(bitmap);
                progressDialog.dismiss();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                Toast.makeText(AfterLogIn.this, "Failed", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });


    }

    public void Method(View view)
    {
        Intent intent=new Intent(AfterLogIn.this,MainActivity.class);
        startActivity(intent);
    }
}
