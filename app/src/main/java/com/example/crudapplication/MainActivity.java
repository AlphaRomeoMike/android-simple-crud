package com.example.crudapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editTextName, EditTextSurname, editTextMarks, editTextId;
    Button buttonAdd, ViewAll, UpdateButton;
    DatabaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        System.out.println(1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Buttons casted
        editTextName = findViewById(R.id.EditTextFirstName);
        EditTextSurname = findViewById(R.id.EditTextLastName);
        editTextMarks = findViewById(R.id.EditTextMarks);
        buttonAdd = findViewById(R.id.fab);
        ViewAll = findViewById(R.id.ViewButton);
        UpdateButton = findViewById(R.id.UpdateButton);
        editTextId = findViewById(R.id.EditTextId);
        myDB = new DatabaseHelper(MainActivity.this);
        //Later
        AddData();
        viewAll();
        updateData();

    }

    public void AddData() {
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isinserted = myDB.insertData(editTextName.getText().toString(),
                        EditTextSurname.getText().toString(),
                        editTextMarks.getText().toString());
                if(isinserted = true) {
                    Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data was not inserted", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void viewAll() {
        ViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.getAllData();
                if(res.getCount() == 0) {
                    showMessage("No data", "Nothing in the database");
                } else {
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append("ID: " + res.getString(0) + "\n");
                        buffer.append("First Name: " + res.getString(1) + "\n");
                        buffer.append("Surname: " + res.getString(2) + "\n");
                        buffer.append("Marks: " + res.getString(3) + "\n\n");
                    }
                    showMessage("Data", buffer.toString());
                }
            }
        });
    }
    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void updateData() {
        UpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDB.updateData(editTextId.getText().toString(),
                        editTextName.getText().toString(),
                        EditTextSurname.getText().toString(),
                        editTextMarks.getText().toString());
                if(isUpdate == true) {
                    Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data updation failed", Toast.LENGTH_LONG).show();
                }
            }

        });
    }

}