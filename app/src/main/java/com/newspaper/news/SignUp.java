package com.newspaper.news;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SignUp extends AppCompatActivity
{
    EditText name,number,password;
    Button button;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name=findViewById(R.id.idsname);
        number=findViewById(R.id.idsnumber);
        password=findViewById(R.id.idspasswword);
        button=findViewById(R.id.idsignup);
        progressDialog=new ProgressDialog(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                creataccount();

            }
        });
    }

    private void creataccount()
    {
        String n=name.getText().toString();
        String num=number.getText().toString();
        String pass=password.getText().toString();
        if(TextUtils.isEmpty(n))
        {
            Toast.makeText(this, "Plz enter name", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(num))
        {
            Toast.makeText(this, "Plz enetr number", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(pass))
        {
            Toast.makeText(this, "Plz enter password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            progressDialog.setTitle("Create Account");
            progressDialog.setMessage("Please wait, while we are checking the credentials.");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            vailidate(n,num,pass);
        }

    }

    private void vailidate(final String n, final String num, final String pass)
    {
        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(!(dataSnapshot.child("User").child(num).exists()))
                {
                    HashMap<String,Object>data=new HashMap<>();
                    data.put("phone",num);
                    data.put("name",n);
                    data.put("password",pass);
                    databaseReference.child("User").child(num).updateChildren(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task)
                        {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(SignUp.this, "Conratulation toy have sucessfully created ypur account", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                Intent intent=new Intent(SignUp.this,LogIn.class);
                                startActivity(intent);
                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(SignUp.this, "Network Error, Plz Try again later", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
                else
                    {
                        Toast.makeText(SignUp.this, "This "+num+" Alredy exists", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        Toast.makeText(SignUp.this, "Plz try it with onother number", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(SignUp.this,LogIn.class);
                        startActivity(intent);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
