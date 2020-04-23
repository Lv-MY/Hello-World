package com.example.qq;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private EditText et_qq;
    private EditText et_pwd;
    private CheckBox cb_remember;
    private static final String tag = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_qq = (EditText) findViewById(R.id.et_qq);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        cb_remember = (CheckBox) findViewById(R.id.cb_remember);
        //SD的读取
        File file = new File(this.getApplicationContext().getExternalFilesDir("lmy")+"/"+"info.txt");
        //cache的读取
       /* File file = new File(this.getApplicationContext().getExternalCacheDir()+"/"+"info.txt");*/
        //内部存储
        /*File file = new File("info.txt");*/
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String info = br.readLine();
            String qq = info.split("##")[0];
            String pws = info.split("##")[1];
            et_qq.setText(qq);
            et_pwd.setText(pws);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void Login(View view) throws IOException {
        String qq = et_qq.getText().toString();
        String pwd = et_pwd.getText().toString();

        if(TextUtils.isEmpty(qq)||TextUtils.isEmpty(pwd)){
            Toast.makeText(this,"QQ账号或密码不能为空！",Toast.LENGTH_LONG).show();
        }else{
            if(cb_remember.isChecked()){
                Log.i(tag, "记住密码！");
                try {
                    String data = qq+"##"+pwd;
                    String filename = "info.txt";
                    //内部存储
                   /* FileOutputStream fos = this.getApplicationContext().openFileOutput(filename, Context.MODE_PRIVATE);*/
                    //外部存储之cache
                   /*FileOutputStream fos = new FileOutputStream(this.getApplicationContext().getExternalCacheDir()+"/"+filename);*/
                    //外部存储之SD
                    FileOutputStream fos = new FileOutputStream(this.getApplicationContext().getExternalFilesDir("lmy")+"/"+filename);

                    System.out.println("##Path##" + this.getApplicationContext().getFilesDir());
                    fos.write(data.getBytes());
                    fos.close();
                    Toast.makeText(this, "保存密码成功", Toast.LENGTH_LONG).show();
                }
                catch(Exception e){
                    e.printStackTrace();
                    Toast.makeText(this,"保存密码失败！",Toast.LENGTH_LONG).show();
                }
            }else{
                Log.i(tag, "无需记住密码！");
            }
        }
    }
}
