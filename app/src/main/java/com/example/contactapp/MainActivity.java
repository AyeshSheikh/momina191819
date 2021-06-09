package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyHelper helper = new MyHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from contact",new String[]{});

        listView = findViewById(R.id.ListView);

        if (cursor.moveToNext() != false){
            ArrayList<String> contactList = new ArrayList<String>();
            do {
                contactList.add(cursor.getString(1));
            }while (cursor.moveToNext());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_list_item_1,contactList
            );
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String id1 = Long.toString(id);
                    Intent intent = new Intent(MainActivity.this,ViewContact.class);
                    intent.putExtra("id",id1);
                    startActivity(intent);
                }
            });
        }

    }


    public void addContacts(View view) {
        Intent intent = new Intent(view.getContext(),AddContact.class);
        intent.putExtra("id",0);
        startActivity(intent);
    }
}