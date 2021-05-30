package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddContact extends AppCompatActivity {

    EditText name,phone,company,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        Intent intent = getIntent();
        String id =intent.getStringExtra("id");
    }

    public void save(View view) {
        name = findViewById(R.id.editTextTextPersonName);
        phone = findViewById(R.id.editTextTextPersonName1);
        company = findViewById(R.id.editTextTextPersonName3);
        email = findViewById(R.id.editTextTextPersonName4);

        ContactModel contactDetails = new ContactModel(
                name.getText().toString(),phone.getText().toString(),company.getText().toString(),email.getText().toString()
        );
        MyHelper helper = new MyHelper(this);
        SQLiteDatabase database = helper.getWritableDatabase();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        helper.insertData(database,contactDetails,id);

        Intent intent1 = new Intent(this,MainActivity.class);
        startActivity(intent1);
    }
}