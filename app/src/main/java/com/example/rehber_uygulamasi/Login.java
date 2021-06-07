package com.example.rehber_uygulamasi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    EditText userName;
    EditText userPassword;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        userName=(EditText) findViewById(R.id.txtUserName);
        userPassword=(EditText) findViewById(R.id.txtPassword);


        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (
                        userName.getText().toString().equals(getString(R.string.userName)) &&
                        userPassword.getText().toString().equals(getString(R.string.userPassword))

                ){
                    Intent intent=new Intent(Login.this,MainActivity.class);
                    startActivity(intent);
                }
                else{
                    AlertDialog.Builder dialog=new AlertDialog.Builder(Login.this);
                    dialog.setTitle("Hatalı Giriş");
                    dialog.setMessage("Kullanıcı Adı veya Parola Hatalı");
                    dialog.setPositiveButton("Tamam",null);
                    dialog.show();
                }

            }
        });
    }
}
