package com.example.cosc341_porject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private EditText et1, et2;
    private Button bt1;
    LocalData localData = null;
    ArrayList<User> users=new ArrayList<User>();
    String enterUsername=null, enterPassword=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        String filePath = "systext.txt";
        FileInputStream ips = null;
        try {
            ips = openFileInput(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectInputStream ois = null;
        try{
            ois = new ObjectInputStream(ips);

            localData = (LocalData) ois.readObject();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(ips != null){
                try {
                    ips.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        users = localData.getUsers();

        et1=findViewById(R.id.et_1);
        et2=findViewById(R.id.et_2);
        bt1=findViewById(R.id.bt_login);

        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                enterUsername = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                enterPassword = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean s = false;
                User user = new User();
                if((enterUsername==null||enterUsername.equals(""))||(enterPassword==null||enterPassword.equals(""))){
                    Toast.makeText(LoginActivity.this, "User Name Cannot Be Empty", Toast.LENGTH_SHORT).show();
                }else{
                    for (int i=0; i<users.size();i++){
                        if(enterUsername.equals(users.get(i).getName())){
                            if(enterPassword.equals(users.get(i).getPassword())){
                                s=true;
                                user = users.get(i);
                            }
                        }
                    }
                    if(s==true){
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("isLogin",s);
                    intent.putExtra("User",user);
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "User Name or PassWord is Wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}