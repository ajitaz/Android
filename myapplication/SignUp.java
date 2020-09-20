package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.io.FileOutputStream;

public class SignUp extends AppCompatActivity {

    private EditText RegName;
    private EditText RegPassword;
    private Button btnReg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        RegName = findViewById(R.id.etRegName);
        RegPassword = findViewById(R.id.etRegPassword);
        btnReg = findViewById(R.id.btnSignUp);

       btnReg.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String regUserName = RegName.getText().toString();
               String regPassword = RegPassword.getText().toString();


                   if (validate(regUserName, regPassword)) {

                       startActivity(new Intent(SignUp.this, MainActivity.class));
                       Toast.makeText(SignUp.this, "SignUp Successful!", Toast.LENGTH_SHORT).show();

                       FileOutputStream fos = null;
                       try {
                           String data = regUserName +","+ regPassword+"\n";
                           fos = openFileOutput("record.txt",MODE_APPEND);
                           fos.write(data.getBytes());
                           Toast.makeText(SignUp.this, "Record Saved Successfully in "+getFilesDir()+" /record.txt", Toast.LENGTH_LONG).show();

                       } catch (Exception e) {
                           e.printStackTrace();
                       }finally{
                           try{
                               if(fos !=null) {
                                   fos.close();
                               }
                           }catch(Exception e){
                               e.printStackTrace();
                           }
                       }
                   }
                   }

       });
    }

    public boolean validate(String userName ,String Password){
        if(userName.isEmpty() || Password.length() < 2){
            Toast.makeText(this,"Enter details Correctly!!!!",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
        }
}