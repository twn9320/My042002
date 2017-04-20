package com.example.yvtc.my042002;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.Permission;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InputStream is = getResources().openRawResource(R.raw.test);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String str = null;
        try {
            str = br.readLine();
            br.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("MFILE", str);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 123)
        {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //取得權限，進行檔案存取
                writeFile();
            } else {
                //使用者拒絕權限，停用檔案存取功能
            }
        }
    }

    public void writeFile()
    {
        File f1 = Environment.getExternalStorageDirectory();
        Log.d("MFILE", "f1:" + f1.toString());
        File f2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        Log.d("MFILE", "f2:" + f2.toString());
        try {
            FileWriter fw = new FileWriter(f1.toString() + File.separator + "test2.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Hello");
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void click1(View v)
    {

        int permission = ActivityCompat.checkSelfPermission(this,
                WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[] {WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE},
                    123
            );

        }
        else
        {
            writeFile();
        }




    }
}
