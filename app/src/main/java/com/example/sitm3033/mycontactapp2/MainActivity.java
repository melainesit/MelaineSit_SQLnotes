package com.example.sitm3033.mycontactapp2;

import android.content.Intent;
import android.database.Cursor;
import android.location.Address;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;
    EditText editAddress;
    EditText editEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MyContactApp", "MainActivity: Starting");
        setContentView(R.layout.activity_main);
        editName = findViewById(R.id.EditText_Name);
        editAddress = findViewById(R.id.EditText_Address);
        editEmail = findViewById(R.id.EditText_Email);


        myDb = new DatabaseHelper(this);
        Log.d("MyContactApp", "MainActivity: instantiating Databasehelper");

    }

    public void addData(View view) {

        Log.d("MyContactApp","MainActivity: Add contact button pressed");

        boolean isInserted = myDb.insertData(editName.getText().toString(), editAddress.getText().toString(), editEmail.getText().toString());

        if(isInserted == true){
            Toast.makeText(MainActivity.this, "Success - contact inserted", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(MainActivity.this, "FAILED - contact not inserted", Toast.LENGTH_LONG).show();
        }
    }
    public void viewData (View view){
        Cursor res = myDb.getAllData();
        Log.d("MyContactApp","MainActivity: viewData : received cursor " + res.getCount());
        if (res.getCount()==0){
            showMessage("Error", "No data found in database");
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            //Append res colum
            for (int i = 0;i<4;i++){
                buffer.append(res.getColumnName(i)+ ": "+ res.getString(i)+ "\n");
            }
            buffer.append("\n");
            /*
            buffer.append(" Contact "+ res.getString(0) + ": " + "/n");
            buffer.append(" Name: "+ res.getString(1)  + "/n");
            buffer.append(" Address: "+ res.getString(2)+ "/n");
            buffer.append(" Number: "+ res.getString(3));
            */
        }
        Log.d("MyContactApp","MainActivity: viewData : assembled stringbuffer ");
        showMessage("Data", buffer.toString());
    }

    private void showMessage(String title, String message) {
        Log.d("MyContactApp","MainActivity: showMessage: building alert dialog");

        AlertDialog.Builder builder =  new AlertDialog.Builder( this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }
    public static final String EXTRA_MESSAGE = "com.example.sitm3033.mycontactapp.MEGGASE";
    public void SearchRecord(View view){
        Log.d("MyContactApp","MainActivity:launching SearchActivity");
        Intent intent = new Intent(this, SearchActivity.class);
        Cursor res = myDb.getAllData();
        StringBuffer buff = new StringBuffer();
        String name = editName.getText().toString();
        while (res.moveToNext()) {
            String ID = res.getString(0);
            String Names = res.getString(1);
            String Address = res.getString(2);
            String Email = res.getString(3);
            if (name.equals(Names)) {
                buff.append(ID + "\n" + Names + "\n" + Address + "\n" + Email + "\n" + "\n");
            }
        }
        if (buff.length() == 0){
            buff.append("Sorry, no results found");
        }
        String retur = buff.toString();

        intent.putExtra(EXTRA_MESSAGE, retur);
        startActivity(intent);
    }

}
