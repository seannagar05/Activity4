package com.example.activity3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText text_username,text_password,text_retype_password;
    Button button_register,button_login,button_go_back;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       text_username = (EditText)findViewById(R.id.txt_username);
        text_password= (EditText)findViewById(R.id.txt_password);
        text_retype_password = (EditText)findViewById(R.id.txt_retype_password);
        button_register = (Button)findViewById(R.id.btn_register);
        button_go_back = (Button)findViewById(R.id.btn_goback);
        DB = new DBHelper(this);

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = text_username.getText().toString();
                String pass = text_password.getText().toString();
                String repassword = text_retype_password.getText().toString();

                if(user.equals("")||pass.equals("")||repassword.equals(""))
                    Toast.makeText(MainActivity.this,"Not all fields are entered", Toast.LENGTH_SHORT).show();
                else{
                        if(pass.equals(repassword)){
                            Boolean checkuser = DB.checkusername(user);

                            if(checkuser==false){
                                Boolean insert = DB.insertData(user,pass);

                                if(insert==true){
                                    Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),Login2.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(MainActivity.this,"Registration failed", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(MainActivity.this,"User Already Registered Please Sign In", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this,"Password does not match", Toast.LENGTH_SHORT).show();
                        }
                }
            }
        });

        button_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),Login2.class);
                startActivity(intent);

            }
        });
    }
}