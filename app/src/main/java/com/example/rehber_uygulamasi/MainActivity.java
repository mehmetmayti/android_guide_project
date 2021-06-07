package com.example.rehber_uygulamasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ListMenuItemView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private ArrayList<Person> persons;
    private ListView listView;
    private Adapter adapter;
    private Button btnAdded;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper db=new DatabaseHelper(getApplicationContext());
        getPerson(db);



        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                PopupMenu pop=new PopupMenu(MainActivity.this,view);
                pop.getMenuInflater().inflate(R.menu.pop_up_menu,pop.getMenu());

                pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId()==R.id.delete){
                            AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                            dialog.setTitle("Uyarı!");
                            dialog.setMessage("Kişiyi Silmek İstediğinize Eminmisiniz ? ");
                            dialog.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    deletePerson(position, db);
                                }
                            });
                            dialog.setNegativeButton("Hayır", null);
                            dialog.show();



                        }else if (item.getItemId()==R.id.update){
                            Person __person=new Person();
                            __person=persons.get(position);
                            SelectedPerson.person=__person;
                            Intent intent=new Intent(MainActivity.this,PersonUpdateScreen.class);
                            startActivity(intent);
                        }
                        else if (item.getItemId()==R.id.details){
                            Person ___person=new Person();
                            ___person=persons.get(position);

                            showAllertDialogDetails(___person);
                        }

                        return false;
                    }
                });
                pop.show();
                return false;
            }
        });

        btnAdded=(Button) findViewById(R.id.btnAddedScreen);
        btnAdded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,PersonAddedScreen.class);
                startActivity(intent);
            }
        });



    }

    private void showAllertDialogDetails(Person person) {
        AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("Kişi Detayı");
        dialog.setMessage(
                "\n\nAd Soyad : "+person.nameSurname+"\n\n"
                +"Tel No : "+person.phoneNumber+"\n\n"
                +"Mail Adresi : "+person.mailAddress+"\n\n"
                +"Kişi Notu : "+person.personNote+"\n\n"
                +"Doğum Tarihi : "+person.dateOfBirth+"\n\n"
                +"Kayıt Tarihi : "+person.addedDate+"\n"
        );
        dialog.setPositiveButton("Tamam",null);
        dialog.show();


    }

    private void getPerson(DatabaseHelper db) {
        persons= (ArrayList<Person>) db.getPersonList();
        listView= findViewById(R.id.listView);
        adapter=new com.example.rehber_uygulamasi.Adapter(getApplicationContext(),persons);
        listView.setAdapter((ListAdapter) adapter);
    }

    private void deletePerson(int position, DatabaseHelper db) {

        Log.i("TAG", "deletePerson: "+persons.get(position).id);

        db.personDelete(persons.get(position).id);
        persons.remove(position);
        getPerson(db);
    }
}