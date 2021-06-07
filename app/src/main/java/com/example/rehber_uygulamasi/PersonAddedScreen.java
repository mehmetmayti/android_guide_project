package com.example.rehber_uygulamasi;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class PersonAddedScreen extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    EditText nameSurname;
    EditText phoneNumber;
    EditText mailAddress;
    EditText personNote;
    EditText addedDate;
    EditText dateOfBirth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_added_screen);
        nameSurname=findViewById(R.id.editTextTextPersonNameSurname);
        phoneNumber=findViewById(R.id.editTextTextPersonPhoneNumber);
        mailAddress=findViewById(R.id.editTextTextPersonMailAddress);
        personNote=findViewById(R.id.editTextTextPersonNote);
        addedDate=findViewById(R.id.editTextTextPersonDateofBirth);
        dateOfBirth=findViewById(R.id.editTextTextPersonDateofBirth);
        DatabaseHelper db=new DatabaseHelper(getApplicationContext());

        String TAG="Uyarı";


        findViewById(R.id.btnDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        findViewById(R.id.personAdded).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (
                        nameSurname.getText().toString().isEmpty()||
                                phoneNumber.getText().toString().isEmpty()||
                                mailAddress.getText().toString().isEmpty()||
                                dateOfBirth.getText().toString().isEmpty()
                )
                {
                    Log.i(TAG,"Yıldızlı yerler boş bırakılamaz");
                }
                else
                 {
                    Person person=new Person();
                    person.nameSurname=nameSurname.getText().toString();
                    person.phoneNumber=phoneNumber.getText().toString();
                    person.mailAddress=mailAddress.getText().toString();
                    person.personNote=personNote.getText().toString();
                    person.addedDate=getDateNow();
                    person.dateOfBirth=dateOfBirth.getText().toString();

                    db.personAdded(person);
                    Intent intent=new Intent(PersonAddedScreen.this,MainActivity.class);
                    startActivity(intent);
                }
                

            }
        });




    }




    private String getDateNow(){
        String dateNow=Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+" "+getStringDate(Calendar.getInstance().get(Calendar.MONTH))+" "+Calendar.getInstance().get(Calendar.YEAR);
        return dateNow;

    }

    private void showDialog() {
        DatePickerDialog dialog=new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                );
        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        EditText editText=(EditText) findViewById(R.id.editTextTextPersonDateofBirth);
       int _month=month+1;
       String _monthStr="";
        _monthStr = getStringDate(_month);
        editText.setText(dayOfMonth+" "+_monthStr+" "+year);
    }

    private String getStringDate(int _month) {
        _month=_month+1;
        if (_month==6){
            return "Haziran";
        }else if(_month==1){
            return "Ocak";
        }
        else if(_month==2){
            return "Şubat";
        }
        else if(_month==3){
            return "Mart";
        }
        else if(_month==4){
            return "Nisan";
        }
        else if(_month==5){
            return "Mayıs";
        }
        else if(_month==7){
            return "Temmuz";
        }
        else if(_month==8){
            return "Ağustos";
        }
        else if(_month==9){
            return "Eylül";
        }
        else if(_month==10){
            return "Ekim";
        }
        else if(_month==11){
            return "Kasım";
        }
        else{
            return "Aralık";
        }
    }
}
