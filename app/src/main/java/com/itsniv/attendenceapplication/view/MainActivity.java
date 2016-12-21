package com.itsniv.attendenceapplication.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.itsniv.attendenceapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init();
    }

    private void Init() {
        Thread t=new Thread(){
            @Override
            public void run() {
                try{
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    Intent intent=new Intent(MainActivity.this,Login.class);
                    startActivity(intent);
                }
            }
        };
        t.start();
    }
}
