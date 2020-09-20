package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class ViewRecords extends AppCompatActivity {


    DatabaseHelper mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_records);

        mydb = new DatabaseHelper(this);

        int buttonNumber = getIntent().getExtras().getInt("buttonNumber");
        switch(buttonNumber){
            case 1: showUsers();
                    break;
            case 2: showRecords();
                    break;
            case 3:
                    evaluateTenant();
                    break;
            case 4:
                    getDatabase();
                    break;
        }




    }

    public void showUsers() {
        TableLayout userTable = findViewById(R.id.eRecTable);
        TableRow tbrow0 = new TableRow(this);

        TextView tv0 = new TextView(this);
        tv0.setText(" Name ");
        tv0.setTextColor(Color.WHITE);
        tbrow0.addView(tv0);

        TextView tv1 = new TextView(this);
        tv1.setText(" Password ");
        tv1.setTextColor(Color.WHITE);
        tbrow0.addView(tv1);

        userTable.addView(tbrow0);

        FileInputStream fis = null;
        try {
            fis = openFileInput("record.txt");
            byte[] reader = new byte[fis.available()];
            while(fis.read(reader)!= -1){}

            Scanner scan  = new Scanner(new String(reader));
            scan.useDelimiter("[,\n]");

            while(scan.hasNext()){
                String name = scan.next();
                String pass = scan.next();

                TableRow tbrow = new TableRow(this);

                TextView t1v = new TextView(this);
                t1v.setText(name);
                t1v.setTextColor(Color.WHITE);
                t1v.setGravity(Gravity.CENTER);
                tbrow.addView(t1v);

                TextView t2v = new TextView(this);
                t2v.setText(pass);
                t2v.setTextColor(Color.WHITE);
                t2v.setGravity(Gravity.CENTER);
                tbrow.addView(t2v);

                userTable.addView(tbrow);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                if(fis!=null) {
                    fis.close();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public void showRecords(){
        TableLayout tbl = findViewById(R.id.eRecTable);
        TableRow headerRow = new TableRow(this);

        TextView col1 = new TextView(this);
        col1.setText(" ID ");
        col1.setTextColor(Color.WHITE);
        col1.setGravity(Gravity.CENTER);
        col1.setPadding(6,4,6,4);
        headerRow.addView(col1);

        TextView col2 = new TextView(this);
        col2.setText(" Name ");
        col2.setTextColor(Color.WHITE);
        col2.setGravity(Gravity.CENTER);
        col2.setPadding(6,4,6,4);
        headerRow.addView(col2);

        TextView col3 = new TextView(this);
        col3.setText(" Phone No. ");
        col3.setTextColor(Color.WHITE);
        col3.setGravity(Gravity.CENTER);
        col3.setPadding(6,4,6,4);
        headerRow.addView(col3);

        TextView col4 = new TextView(this);
        col4.setText(" Date ");
        col4.setTextColor(Color.WHITE);
        col4.setGravity(Gravity.CENTER);
        col4.setPadding(6,4,6,4);
        headerRow.addView(col4);

        TextView col5 = new TextView(this);
        col5.setText(" Room ");
        col5.setTextColor(Color.WHITE);
        col5.setGravity(Gravity.CENTER);
        col5.setPadding(6,4,6,4);
        headerRow.addView(col5);

        TextView col6 = new TextView(this);
        col6.setText(" Prev Unit ");
        col6.setTextColor(Color.WHITE);
        col6.setGravity(Gravity.CENTER);
        col6.setPadding(6,4,6,4);
        headerRow.addView(col6);

        TextView col7 = new TextView(this);
        col7.setText(" Current Unit ");
        col7.setTextColor(Color.WHITE);
        col7.setGravity(Gravity.CENTER);
        col7.setPadding(6,4,6,4);
        headerRow.addView(col7);

        tbl.addView(headerRow);

        FileInputStream fis = null;
        try {
            fis = openFileInput("Tenant.txt");
            byte[] reader = new byte[fis.available()];
            while(fis.read(reader)!= -1){}

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

                TableRow tableRow = new TableRow(this);

                TextView tv1 = new TextView(this);
                tv1.setText(id);
                tv1.setTextColor(Color.GREEN);
                tv1.setGravity(Gravity.CENTER);
                tv1.setPadding(6,4,6,4);
                tableRow.addView(tv1);

                TextView tv2 = new TextView(this);
                tv2.setText(name);
                tv2.setTextColor(Color.GREEN);
                tv2.setGravity(Gravity.CENTER);
                tv2.setPadding(6,4,6,4);
                tableRow.addView(tv2);

                TextView tv3 = new TextView(this);
                tv3.setText(phNo);
                tv3.setTextColor(Color.GREEN);
                tv3.setGravity(Gravity.CENTER);
                tv3.setPadding(6,4,6,4);
                tableRow.addView(tv3);

                TextView tv4 = new TextView(this);
                tv4.setText(date);
                tv4.setTextColor(Color.GREEN);
                tv4.setGravity(Gravity.CENTER);
                tv4.setPadding(6,4,6,4);
                tableRow.addView(tv4);

                TextView tv5 = new TextView(this);
                tv5.setText(room);
                tv5.setTextColor(Color.GREEN);
                tv5.setGravity(Gravity.CENTER);
                tv5.setPadding(6,4,6,4);
                tableRow.addView(tv5);

                TextView tv6 = new TextView(this);
                tv6.setText(prevUnit);
                tv6.setTextColor(Color.GREEN);
                tv6.setGravity(Gravity.CENTER);
                tv6.setPadding(6,4,6,4);
                tableRow.addView(tv6);

                TextView tv7 = new TextView(this);
                tv7.setText(cUnit);
                tv7.setTextColor(Color.GREEN);
                tv7.setGravity(Gravity.CENTER);
                tv7.setPadding(6,4,6,4);
                tableRow.addView(tv7);

                tbl.addView(tableRow);
            }

            scan.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void evaluateTenant(){
        TableLayout evaluateTable = findViewById(R.id.eRecTable);
        TableRow header = new TableRow(this);

        TextView col1 = new TextView(this);
        col1.setText("ID");
        col1.setGravity(Gravity.CENTER);
        col1.setTextColor(Color.YELLOW);
        col1.setPadding(6,4,6,4);
        header.addView(col1);

        TextView col2 = new TextView(this);
        col2.setText("Name");
        col2.setGravity(Gravity.CENTER);
        col2.setTextColor(Color.YELLOW);
        col2.setPadding(6,4,6,4);
        header.addView(col2);

        TextView col3 = new TextView(this);
        col3.setText("Unit");
        col3.setGravity(Gravity.CENTER);
        col3.setTextColor(Color.YELLOW);
        col3.setPadding(6,4,6,4);
        header.addView(col3);

        TextView col4 = new TextView(this);
        col4.setText("Unit Amount");
        col4.setGravity(Gravity.CENTER);
        col4.setTextColor(Color.YELLOW);
        col4.setPadding(6,4,6,4);
        header.addView(col4);

        TextView col5 = new TextView(this);
        col5.setText("Rent Amount");
        col5.setGravity(Gravity.CENTER);
        col5.setTextColor(Color.YELLOW);
        col5.setPadding(6,4,6,4);
        header.addView(col5);

        TextView col6 = new TextView(this);
        col6.setText("Total");
        col6.setGravity(Gravity.CENTER);
        col6.setTextColor(Color.YELLOW);
        col6.setPadding(6,4,6,4);
        header.addView(col6);

        evaluateTable.addView(header);

        FileInputStream fis = null;
        try {
            fis = openFileInput("Tenant.txt");
            byte[] reader = new byte[fis.available()];
            while(fis.read(reader)!= -1 ){}

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

                String unit = String.valueOf(Double.parseDouble(cUnit)- Double.parseDouble(prevUnit));
                String unitAmt = String.valueOf(Double.parseDouble(unit)*11);
                if(Double.parseDouble(unitAmt)<50){
                    unitAmt = "50";
                }

                String rentAmt = String.valueOf(Double.parseDouble(room)*3000);
                switch(Integer.parseInt(id)){
                    case 8:
                         rentAmt = String.valueOf(Double.parseDouble(room)*2500);
                        break;
                    case 9:
                        rentAmt = String.valueOf(Double.parseDouble(room)*2000);
                        break;
                }

                String total = String.valueOf(Double.parseDouble(unitAmt)+Double.parseDouble(rentAmt));


                TableRow tableRow = new TableRow(this);

                TextView tv1 =new TextView(this);
                tv1.setText(id);
                tv1.setTextColor(Color.WHITE);
                tv1.setGravity(Gravity.CENTER);
                tv1.setPadding(6,4,6,4);
                tableRow.addView(tv1);

                TextView tv2 =new TextView(this);
                tv2.setText(name);
                tv2.setTextColor(Color.WHITE);
                tv2.setGravity(Gravity.CENTER);
                tv2.setPadding(6,4,6,4);
                tableRow.addView(tv2);

                TextView tv3 =new TextView(this);
                tv3.setText(unit);
                tv3.setTextColor(Color.WHITE);
                tv3.setGravity(Gravity.CENTER);
                tv3.setPadding(6,4,6,4);
                tableRow.addView(tv3);

                TextView tv4 =new TextView(this);
                tv4.setText(unitAmt);
                tv4.setTextColor(Color.WHITE);
                tv4.setGravity(Gravity.CENTER);
                tv4.setPadding(6,4,6,4);
                tableRow.addView(tv4);

                TextView tv5 =new TextView(this);
                tv5.setText(rentAmt);
                tv5.setTextColor(Color.WHITE);
                tv5.setGravity(Gravity.CENTER);
                tv5.setPadding(6,4,6,4);
                tableRow.addView(tv5);

                TextView tv6 =new TextView(this);
                tv6.setText(total);
                tv6.setTextColor(Color.WHITE);
                tv6.setGravity(Gravity.CENTER);
                tv6.setPadding(6,4,6,4);
                tableRow.addView(tv6);

                evaluateTable.addView(tableRow);

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
        }

    }

    public void getDatabase(){
        TableLayout tbl = findViewById(R.id.eRecTable);
        TableRow headerRow = new TableRow(this);

        TextView col1 = new TextView(this);
        col1.setText(" ID ");
        col1.setTextColor(Color.WHITE);
        col1.setGravity(Gravity.CENTER);
        col1.setPadding(6,4,6,4);
        headerRow.addView(col1);

        TextView col2 = new TextView(this);
        col2.setText(" Name ");
        col2.setTextColor(Color.WHITE);
        col2.setGravity(Gravity.CENTER);
        col2.setPadding(6,4,6,4);
        headerRow.addView(col2);

        TextView col3 = new TextView(this);
        col3.setText(" Phone No. ");
        col3.setTextColor(Color.WHITE);
        col3.setGravity(Gravity.CENTER);
        col3.setPadding(6,4,6,4);
        headerRow.addView(col3);

        TextView col4 = new TextView(this);
        col4.setText(" Date ");
        col4.setTextColor(Color.WHITE);
        col4.setGravity(Gravity.CENTER);
        col4.setPadding(6,4,6,4);
        headerRow.addView(col4);

        TextView col5 = new TextView(this);
        col5.setText(" Room ");
        col5.setTextColor(Color.WHITE);
        col5.setGravity(Gravity.CENTER);
        col5.setPadding(6,4,6,4);
        headerRow.addView(col5);

        TextView col6 = new TextView(this);
        col6.setText(" Prev Unit ");
        col6.setTextColor(Color.WHITE);
        col6.setGravity(Gravity.CENTER);
        col6.setPadding(6,4,6,4);
        headerRow.addView(col6);

        TextView col7 = new TextView(this);
        col7.setText(" Current Unit ");
        col7.setTextColor(Color.WHITE);
        col7.setGravity(Gravity.CENTER);
        col7.setPadding(6,4,6,4);
        headerRow.addView(col7);

        tbl.addView(headerRow);
        Cursor res = null;
        try {
                res = mydb.getAllData();
        }catch(NullPointerException ne){
            ne.printStackTrace();
            Toast.makeText(ViewRecords.this, "Problem here", Toast.LENGTH_SHORT).show();
        }


       while(res.moveToNext()){
           String id = res.getString(0);
           String name = res.getString(1);
           String phNo = res.getString(2);
           String date = res.getString(3);
           String room = res.getString(4);
           String prevUnit = res.getString(5);
           String cUnit = res.getString(6);

           TableRow tableRow = new TableRow(this);

           TextView tv1 = new TextView(this);
           tv1.setText(id);
           tv1.setTextColor(Color.GREEN);
           tv1.setGravity(Gravity.CENTER);
           tv1.setPadding(6,4,6,4);
           tableRow.addView(tv1);

           TextView tv2 = new TextView(this);
           tv2.setText(name);
           tv2.setTextColor(Color.GREEN);
           tv2.setGravity(Gravity.CENTER);
           tv2.setPadding(6,4,6,4);
           tableRow.addView(tv2);

           TextView tv3 = new TextView(this);
           tv3.setText(phNo);
           tv3.setTextColor(Color.GREEN);
           tv3.setGravity(Gravity.CENTER);
           tv3.setPadding(6,4,6,4);
           tableRow.addView(tv3);

           TextView tv4 = new TextView(this);
           tv4.setText(date);
           tv4.setTextColor(Color.GREEN);
           tv4.setGravity(Gravity.CENTER);
           tv4.setPadding(6,4,6,4);
           tableRow.addView(tv4);

           TextView tv5 = new TextView(this);
           tv5.setText(room);
           tv5.setTextColor(Color.GREEN);
           tv5.setGravity(Gravity.CENTER);
           tv5.setPadding(6,4,6,4);
           tableRow.addView(tv5);

           TextView tv6 = new TextView(this);
           tv6.setText(prevUnit);
           tv6.setTextColor(Color.GREEN);
           tv6.setGravity(Gravity.CENTER);
           tv6.setPadding(6,4,6,4);
           tableRow.addView(tv6);

           TextView tv7 = new TextView(this);
           tv7.setText(cUnit);
           tv7.setTextColor(Color.GREEN);
           tv7.setGravity(Gravity.CENTER);
           tv7.setPadding(6,4,6,4);
           tableRow.addView(tv7);

           tbl.addView(tableRow);


       }

    }


}