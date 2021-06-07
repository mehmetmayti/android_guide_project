package com.example.rehber_uygulamasi;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int databaseVersion = 1;
    private static String databaseName="rehber_database";

    private static String tableName="rehber";
    private static String personId="id";
    private static String nameSurname="nameSurname";
    private static String phoneNumber="phoneNumber";
    private static String mailAddress="mailAddress";
    private static String personNote="personNote";
    private static String addedDate="addedDate";
    private static String dateOfBirth="dateOfBirth";


    public DatabaseHelper(@Nullable Context context) {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE="create table "+tableName+"("+personId+" integer primary key autoincrement,"+nameSurname+" text,"
                +phoneNumber+" text,"+mailAddress+" text,"+personNote+" text,"+addedDate+" text,"+dateOfBirth+" text"+")";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void personDelete(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(tableName,personId+" = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void personAdded(Person person){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(nameSurname,person.nameSurname);
        values.put(phoneNumber,person.phoneNumber);
        values.put(mailAddress,person.mailAddress);
        values.put(personNote,person.personNote);
        values.put(addedDate,person.addedDate);
        values.put(dateOfBirth,person.dateOfBirth);

        db.insert(tableName,null,values);
        db.close();
    }

    public List<Person> getPersonList(){
        String[] columns={personId,nameSurname,phoneNumber,mailAddress,personNote,addedDate,dateOfBirth};
        List<Person> personList=new ArrayList<Person>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query(tableName,columns,null,null,null,null,null);
        while (cursor.moveToNext()){
            Person person=new Person();
            person.id=Integer.parseInt(cursor.getString(cursor.getColumnIndex(personId)));
            person.nameSurname=cursor.getString(cursor.getColumnIndex(nameSurname));
            person.phoneNumber=cursor.getString(cursor.getColumnIndex(phoneNumber));
            person.mailAddress=cursor.getString(cursor.getColumnIndex(mailAddress));
            person.personNote=cursor.getString(cursor.getColumnIndex(personNote));
            person.addedDate=cursor.getString(cursor.getColumnIndex(addedDate));
            person.dateOfBirth=cursor.getString(cursor.getColumnIndex(dateOfBirth));

            personList.add(person);
        }

        return personList;
    }
    public void personUpdate(Person person){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(nameSurname,person.nameSurname);
        values.put(phoneNumber,person.phoneNumber);
        values.put(mailAddress,person.mailAddress);
        values.put(personNote,person.personNote);
        values.put(dateOfBirth,person.dateOfBirth);
        Log.i("TAG", "personUpdate: "+person.id);
        db.update(tableName,values,personId+" = ?", new String[]{String.valueOf(person.id)});
        db.close();

    }


}
