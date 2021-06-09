package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ViewContact extends AppCompatActivity {

    TextView name,phone,company,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);
        Intent intent = getIntent();
        String idString = intent.getStringExtra("id");
        int id = Integer.valueOf(idString);
        id++;

        name = findViewById(R.id.textView7);
        phone = findViewById(R.id.textView8);
        company = findViewById(R.id.textView9);
        email = findViewById(R.id.textView10);

        MyHelper helper = new MyHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from contact",new String[]{});
        cursor.moveToFirst();

        for (int i = 2;i <= id; i++){
            cursor.moveToNext();
        }

        name.setText(cursor.getString(1));
        phone.setText(cursor.getString(2));
        company.setText(cursor.getString(3));
        email.setText(cursor.getString(4));
    }

    public void delete(View view) {
        Intent intent = getIntent();
        String idString = intent.getStringExtra("id");
        int id = Integer.valueOf(idString);
        id++;
        MyHelper helper = new MyHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from contact",new String[]{});
        cursor.moveToFirst();

        for (int i = 2;i <= id; i++){
            cursor.moveToNext();
        }
        int _id = cursor.getInt(0);
        helper.delete(db,String.valueOf(_id));
        Intent intent1 = new Intent(this,MainActivity.class);
        startActivity(intent1);
    }

    public void edit(View view) {
        Intent intent = getIntent();
        String idString = intent.getStringExtra("id");
        int id = Integer.valueOf(idString);
        id++;

        Intent intent1 = new Intent(view.getContext(),AddContact.class);
        intent1.putExtra("id",String.valueOf(id));
        startActivity(intent1);
    }
}