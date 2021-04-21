package com.example.activity3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login2 extends AppCompatActivity {

    EditText text_username, text_password;
    Button button_login,button_register;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        text_username = (EditText) findViewById(R.id.txt_username1);
        text_password = (EditText) findViewById(R.id.txt_password1);
        button_login = (Button) findViewById(R.id.btn_login);
        button_register = (Button) findViewById(R.id.btn_register2);
        DB = new DBHelper(this);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = text_username.getText().toString();
                String pass = text_password.getText().toString();
                if(user.equals("")||pass.equals(""))
                    Toast.makeText(Login2.this, "Not all fields are entered", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user,pass);
                    if(checkuserpass==true){
                        Toast.makeText(Login2.this, "Login Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login2.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });


    }
}