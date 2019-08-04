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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.newspaper.news.Model.Users;

public class LogIn extends AppCompatActivity
{
    Button login,signup;
   EditText password,number;
   TextView guest;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        login=findViewById(R.id.idlogin);
        signup=findViewById(R.id.idsignup);
        password=findViewById(R.id.idpassword);
        number=findViewById(R.id.idnumber);
        guest=findViewById(R.id.idguest);
        progressDialog=new ProgressDialog(this);
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(LogIn.this,AfterLogIn.class);
                startActivity(intent);

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(LogIn.this,SignUp.class);
                startActivity(intent);

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Accountlogin();
            }
        });
    }

    private void Accountlogin()
    {
        String phone=number.getText().toString();
        String pass=password.getText().toString();
        if(TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "Plz enter phone number", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(pass))
        {
            Toast.makeText(this, "Plz enter password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            progressDialog.setTitle("Signing In");
            progressDialog.setMessage("Please wait, while we are checking the credentials.");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            validate(phone,pass);
        }

    }

    private void validate(final String phone, final String pass)
    {
        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.child("User").child(phone).exists())
                {
                    Users users=dataSnapshot.child("User").child(phone).getValue(Users.class);
                    if(users.getPhone().equals(phone))
                    {
                        if(users.getPassword().equals(pass))
                        {
                            Toast.makeText(LogIn.this, "Welcome", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            password.setText("");
                            number.setText("");
                            Intent intent=new Intent(LogIn.this,AfterLogIn.class);
                            startActivity(intent);
                        }
                        else
                        {
                            progressDialog.dismiss();
                            Toast.makeText(LogIn.this, "Bad Credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                    {
                        Toast.makeText(LogIn.this, "Account with "+phone+" Alredy exists", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
