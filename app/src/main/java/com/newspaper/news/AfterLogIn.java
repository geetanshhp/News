package com.newspaper.news;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AfterLogIn extends AppCompatActivity
{
    TextView time,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_after_log_in);
        date=findViewById(R.id.idate);
        time=findViewById(R.id.idtime);
        Calendar calendar=Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("hh:mm:ss");
        String val=simpleDateFormat.format(calendar.getTime());
        date.setText(currentDate);
        time.setText(val);

    }

    public void Method(View view)
    {
        Intent intent=new Intent(AfterLogIn.this,MainActivity.class);
        startActivity(intent);
    }
}
