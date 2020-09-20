package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private EditText eUName;
    private EditText ePassword;
    private Button bLogin;
    private TextView attemptInfo;
    private TextView eRegister;

    private boolean isValid = false;
    private int count = 5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eUName = findViewById(R.id.etUserName);
        ePassword = findViewById(R.id.etPassword);
        bLogin = findViewById(R.id.btnLogin);
        attemptInfo = findViewById(R.id.tvAttempt);
        eRegister = findViewById(R.id.tvRegister);

        eRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SignUp.class));
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputUname = eUName.getText().toString();
                String inputPassword = ePassword.getText().toString();

                if(inputUname.isEmpty() || inputPassword.isEmpty()){
                    Toast.makeText(MainActivity.this,"Enter all details correctly! ", Toast.LENGTH_SHORT).show();
                }
                else{
                    isValid = validate(inputUname,inputPassword);

                    if(!isValid){
                        count--;
                        Toast.makeText(MainActivity.this,"Wrong credential!!!",Toast.LENGTH_SHORT).show();
                        attemptInfo.setText("No. of Attempts  remaining: "+count);

                        if(count == 0){
                            bLogin.setEnabled(false);
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Login Successful! ", Toast.LENGTH_SHORT).show();
                        Intent landing = new Intent(MainActivity.this,LandingPage.class);
                        startActivity(landing);
                    }
                }

            }
        });
    }

    public boolean validate(String user ,String pass) {
//        if (SignUp.cred != null) {
            FileInputStream fis = null;
            try {
                fis = openFileInput("record.txt");
                byte[] reader = new byte[fis.available()];
                while(fis.read(reader) != -1){} // copying data of record.txt file

                Scanner scan = new Scanner(new String(reader));
                scan.useDelimiter("[,\n]");

                while(scan.hasNext()){
                    String uname = scan.next();
                    String upass = scan.next();
                    if(user.equals(uname) && pass.equals(upass)){
                        return true;
                    }
                }
                scan.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


//       }
        return false;
    }
}