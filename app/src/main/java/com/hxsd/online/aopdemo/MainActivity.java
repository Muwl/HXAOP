package com.hxsd.online.aopdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hxsd.online.hxaop.annotation.Trace;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getNum(100000);
    }

    @Trace
    private int getNum(int j){
        int m=0;
        for (int i=0;i<j;i++){
            m=i+m;
        }
        return m;
    };
}
