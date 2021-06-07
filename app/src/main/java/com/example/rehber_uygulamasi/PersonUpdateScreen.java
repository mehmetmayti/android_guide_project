package com.example.rehber_uygulamasi;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PersonUpdateScreen extends AppCompatActivity {
    Person person=new Person();
    DatabaseHelper db=new DatabaseHelper(this);
    EditText nameSurname;
    EditText phoneNumber;
    EditText mailAddress;
    EditText personNote;
    EditText dateOfBirth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_udate_screen);
        person=SelectedPerson.person;
        nameSurname=findViewById(R.id.editTextTextPersonNameSurname);
        phoneNumber=findViewById(R.id.editTextTextPersonPhoneNumber);
        mailAddress=findViewById(R.id.editTextTextPersonMailAddress);
        personNote=findViewById(R.id.editTextTextPersonNote);
        dateOfBirth=findViewById(R.id.editTextTextPersonDateofBirth);

        setContentPerson();

    }

    private void setContentPerson() {

        nameSurname.setText(person.nameSurname);
        phoneNumber.setText(person.phoneNumber);
        mailAddress.setText(person.mailAddress);
        personNote.setText(person.personNote);
        dateOfBirth.setText(person.dateOfBirth);

        findViewById(R.id.personUpdated).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person _person=new Person();
                _person.id=person.id;
                _person.nameSurname=nameSurname.getText().toString();
                _person.phoneNumber=phoneNumber.getText().toString();
                _person.mailAddress=mailAddress.getText().toString();
                _person.personNote=personNote.getText().toString();
                _person.dateOfBirth=dateOfBirth.getText().toString();
                db.personUpdate(_person);
                Intent intent=new Intent(PersonUpdateScreen.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
