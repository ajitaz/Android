package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class LandingPage extends AppCompatActivity {

       Button showTable;
       Button addTenant;
       Button editTenant;
       Button evaluate;
       Button tenantRecord;
       Button database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        showTable = findViewById(R.id.btntable);
        addTenant = findViewById(R.id.ebtnaddTenant);
        editTenant = findViewById(R.id.ebtneditTenant);
        evaluate = findViewById(R.id.ebtnEvaluate);
        tenantRecord = findViewById(R.id.ebtnRecord);
        database = findViewById(R.id.ebtnDatabase);

        showTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentViewRecord = new Intent(LandingPage.this,ViewRecords.class);
                intentViewRecord.putExtra("buttonNumber",1);
                startActivity(intentViewRecord);

            }
        });

        addTenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LandingPage.this,AddTenant.class));
            }
        });

        tenantRecord.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intentTenantRec = new Intent(LandingPage.this,ViewRecords.class);
                intentTenantRec.putExtra("buttonNumber",2);
                startActivity(intentTenantRec);
            }
        });

        editTenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LandingPage.this,EditTenant.class));
            }
        });

        evaluate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view){
               Intent evaluateIntent = new Intent(LandingPage.this,ViewRecords.class);
               evaluateIntent.putExtra("buttonNumber",3);
               startActivity(evaluateIntent);
           }
        });

        database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent databaseIntent = new Intent(LandingPage.this,ViewRecords.class);
                databaseIntent.putExtra("buttonNumber",4);
                startActivity(databaseIntent);
            }
        });


    }


}