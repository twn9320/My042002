package com.example.yvtc.my042002;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputStream is = getResources().openRawResource(R.raw.test);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String str = null;
        try {
            str=br.readLine();
            br.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
       Log.d("Myfile:",str);
    }
}
