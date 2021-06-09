package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
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

        if (id != null){
            name = findViewById(R.id.editTextTextPersonName);
            phone = findViewById(R.id.editTextTextPersonName1);
            company = findViewById(R.id.editTextTextPersonName3);
            email = findViewById(R.id.editTextTextPersonName4);

            MyHelper helper = new MyHelper(this);
            SQLiteDatabase db = helper.getWritableDatabase();
            Cursor cursor = db.rawQuery("select * from contact",new String[]{});
            cursor.moveToFirst();
            for (int i = 2;i <= Integer.valueOf(id); i++){
                cursor.moveToNext();
            }
            name.setText(cursor.getString(1));
            phone.setText(cursor.getString(2));
            company.setText(cursor.getString(3));
            email.setText(cursor.getString(4));
        }
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