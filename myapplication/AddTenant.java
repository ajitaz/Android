package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddTenant extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText ID, Name, PhoneNo, Date, Room, Unit;
    Button btnAdd,btnDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tenant);
        ID = findViewById(R.id.etId);
        Name = findViewById(R.id.etName);
        PhoneNo = findViewById(R.id.etPhNo);
        Date = findViewById(R.id.etDate);
        Room = findViewById(R.id.etRoom);
        Unit = findViewById(R.id.etUnit);
        btnAdd = findViewById(R.id.ebtnAdd);
        btnDatabase = findViewById(R.id.ebtnDatabase);

        myDb = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTenant();
            }
        });

        btnDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               addToDatabase();
            }
        });


    }

    public void addTenant(){
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("Tenant.txt",MODE_APPEND);
            String data = ID.getText().toString()+","+Name.getText().toString()+","+PhoneNo.getText().toString()+","+Date.getText().toString()+","+Room.getText().toString()+","+"0"+","+Unit.getText().toString()+"\n";
            fos.write(data.getBytes());
            Toast.makeText(AddTenant.this, "Record Saved Successfully in "+getFilesDir()+" /Tenant.txt", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void addToDatabase(){
        String name = Name.getText().toString();
        String phNo = PhoneNo.getText().toString();
        String date = Date.getText().toString();
        String room = Room.getText().toString();
        String cUnit = Unit.getText().toString();
        String prevUnit = "0";

        boolean isInserted = myDb.insertData(name,phNo,date,room,prevUnit,cUnit);
        if(isInserted){
            Toast.makeText(AddTenant.this, "Data Inserted", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(AddTenant.this, "Not Inserted!!!!!", Toast.LENGTH_SHORT).show();
        }
    }



}