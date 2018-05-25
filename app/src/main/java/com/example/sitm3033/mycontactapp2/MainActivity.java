package com.example.sitm3033.mycontactapp2;

import android.database.Cursor;
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
    EditText editNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MyContactApp", "MainActivity: Starting");
        setContentView(R.layout.activity_main);
        editName = findViewById(R.id.EditText_Name);
        editAddress = findViewById(R.id.EditText_Address);
        editNumber = findViewById(R.id.EditText_Number);


        myDb = new DatabaseHelper(this);
        Log.d("MyContactApp", "MainActivity: instantiating Databasehelper");

    }

    public void addData(View view) {
        Log.d("MyContactApp","MainActivity: Add contact button pressed");

        boolean isInserted = myDb.insertData(editName.getText().toString());

        if(isInserted == true){
            Toast.makeText(MainActivity.this, "Success - contact inserted", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(MainActivity.this, "FAILED - contact inserted", Toast.LENGTH_LONG).show();
        }
    }
    public void viewData (View view){
        Cursor res = myDb.getAllData();
        Log.d("MyContactApp","MainActivity: viewData : recieved cursor " + res.getCount());
        if (res.getCount()==0){
            showMessage("Error", "No data found in database");
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            //Append res colum
        }
        Log.d("MyContactApp","MainActivity: viewData : assembled stringbuffer " + res.getCount());
        showMessage("Data", buffer.toString());
    }

    private void showMessage(String error, String no_data_found_in_database) {
        Log.d("MyContactApp","MainActivity: showMessage: building alert dialog");

        AlertDialog.Builder builder =  new AlertDialog.Builder( this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);

    }

}
