package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class EditTenant extends AppCompatActivity {

    DatabaseHelper mydb;

    EditText ID ,Name ,PhNo ,Date ,Room ,PrevUnit ,CUnit;
    Button  btnCheck ,btnEdit ,btnDelete,btnUpdate,btnDbdelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tenant);

        mydb = new DatabaseHelper(this);

        ID = findViewById(R.id.etId);
        Name = findViewById(R.id.etName);
        PhNo = findViewById(R.id.etPhNo);
        Date = findViewById(R.id.etDate);
        Room = findViewById(R.id.etRoom);
        PrevUnit = findViewById(R.id.etPrevUnit);
        CUnit = findViewById(R.id.etCUnit);
        btnCheck = findViewById(R.id.ebtnCheck);
        btnEdit = findViewById(R.id.ebtnEdit);
        btnDelete = findViewById(R.id.ebtnDelete);
        btnUpdate = findViewById(R.id.ebtnUpdate);
        btnDbdelete = findViewById(R.id.ebtnDbdelete);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkTenant();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteTenant();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ID.getText()!=null) {
                    editTenant();
                }
                else{
                    Toast.makeText(EditTenant.this,"Please Enter the ID!!!!!!!!!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                updateData();
            }
        });

        btnDbdelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                deleteData();
            }
        });


    }

    public void checkTenant(){
        FileInputStream fis = null;
        try {
            fis = openFileInput("Tenant.txt");
            byte[] reader = new byte[fis.available()];
            while(fis.read(reader)!=-1){ }

            Scanner scan = new Scanner(new String(reader));
            scan.useDelimiter("[,\n]");

            while(scan.hasNext()){
                String id = scan.next();
                String name = scan.next();
                String phNo = scan.next();
                String date = scan.next();
                String room = scan.next();
                String prevUnit = scan.next();
                String cUnit = scan.next();

                if(id.equals(ID.getText().toString())){
                    Name.setText(name);
                    PhNo.setText(phNo);
                    Date.setText(date);
                    Room.setText(room);
                    PrevUnit.setText(prevUnit);
                    CUnit.setText(cUnit);
                    break;
                }

            }
            scan.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void deleteTenant(){

        String tempFile = "temp.txt";
        FileInputStream fis = null;
        FileOutputStream fos =null;;
        try {
            fis = openFileInput("Tenant.txt");
            fos = openFileOutput(tempFile,MODE_APPEND);

            byte[] reader = new byte[fis.available()];
            while(fis.read(reader)!=-1){}

            Scanner scan = new Scanner(new String(reader));
            scan.useDelimiter("[,\n]");

            while(scan.hasNext()){
                String id = scan.next();
                String name = scan.next();
                String phNo = scan.next();
                String date = scan.next();
                String room = scan.next();
                String prevUnit = scan.next();
                String cUnit = scan.next();

                String data = id+","+name+","+phNo+","+date+","+room+","+prevUnit+","+cUnit+"\n";

                if(!(id.equals(ID.getText().toString()))){
                    fos.write(data.getBytes());
                }
            }
            scan.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos!=null){
                try {
                    fos.close();
                    deleteFile("Tenant.txt");
                    File temporaryFile = new File(getFilesDir(),tempFile);
                    File tenantFile = new File(getFilesDir(),"Tenant.txt");
                   boolean check= temporaryFile.renameTo(tenantFile);
                   if(check){
                       Toast.makeText(EditTenant.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                   }else{
                       Toast.makeText(EditTenant.this, "Could Not Delete!!!!!", Toast.LENGTH_SHORT).show();
                   }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }

    }

    public void editTenant(){
        String tempFile = "temp.txt";
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = openFileInput("Tenant.txt");
            fos = openFileOutput(tempFile,MODE_APPEND);

            byte[] reader = new byte[fis.available()];
            while(fis.read(reader)!=-1){}

            Scanner scan = new Scanner(new String(reader));
            scan.useDelimiter("[,\n]");

            while(scan.hasNext()){
                String id = scan.next();
                String name = scan.next();
                String phNo = scan.next();
                String date = scan.next();
                String room = scan.next();
                String prevUnit = scan.next();
                String cUnit = scan.next();

                String data = id+","+name+","+phNo+","+date+","+room+","+prevUnit+","+cUnit+"\n";

                if(id.equals(ID.getText().toString())){
                    data = ID.getText().toString()+","+Name.getText().toString()+","+PhNo.getText().toString()+","+Date.getText().toString()+","+Room.getText().toString()+","+PrevUnit.getText().toString()+","+CUnit.getText().toString()+"\n";
                }

                fos.write(data.getBytes());

            }
            scan.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos!=null){
                try {
                    fos.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            deleteFile("Tenant.txt");
            File temporaryFile = new File(getFilesDir(),tempFile);
            File tenantFile = new File(getFilesDir(),"Tenant.txt");
            boolean check = temporaryFile.renameTo(tenantFile);
            if(check){
                Toast.makeText(EditTenant.this, "SuccessFully Edited.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(EditTenant.this, "Could Not Edit!!!", Toast.LENGTH_SHORT).show();
            }
        }

   }

   public void updateData(){
        String id = ID.getText().toString();
        String name = Name.getText().toString();
        String phNo = PhNo.getText().toString();
        String date = Date.getText().toString();
        String room = Room.getText().toString();
        String prevUnit = PrevUnit.getText().toString();
        String cUnit = CUnit.getText().toString();

        boolean isUpdate = mydb.updateDatabase(id,name,phNo,date,room,prevUnit,cUnit);
        if(isUpdate){
            Toast.makeText(EditTenant.this, "Successfully Updated!!!!!!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(EditTenant.this, "Could Not Update!!!!!!", Toast.LENGTH_SHORT).show();
        }
   }

   public void deleteData(){
        String id = ID.getText().toString();
        Integer delete = mydb.deleteData(id);
        if(delete == 0 ){
            Toast.makeText(EditTenant.this, "Could not delete!!!!!!!!!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(EditTenant.this, "Successfully deleted....", Toast.LENGTH_SHORT).show();
        }
   }
}