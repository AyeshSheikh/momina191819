package com.example.contactapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {
    public MyHelper(Context context) {

        super(context,"myDB",null,3);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "Create table contact (_id Integer Primary Key AutoIncrement, Name text, Phone text, Company text, Email text)";
        db.execSQL(query);
    }

    public void insertData (SQLiteDatabase db,ContactModel contactDetails,String id) {
        ContentValues content = new ContentValues();
        content.put("Name",contactDetails.getName());
        content.put("Phone",contactDetails.getPhone());
        content.put("Company",contactDetails.getCompany());
        content.put("Email",contactDetails.getEmail());
        if (id == null){
            db.insert("contact",null,content);
        }
        else {
            db.update("contact",content,"_id=?",new String[]{id});
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void delete(SQLiteDatabase db, String valueOf) {
    }
}
