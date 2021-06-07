package com.example.rehber_uygulamasi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends ArrayAdapter<Person> {

    private final LayoutInflater inflater;
    private final Context context;
    private ViewHolder holder;
    private final ArrayList<Person> persons;

    public Adapter(@NonNull Context context, @NonNull List<Person> persons) {
        super(context,0, persons);
        this.context = context;
        this.persons = (ArrayList<Person>) persons;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Person getItem(int position) {
        return persons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return persons.get(position).hashCode();
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.listview_item, null);

            holder = new ViewHolder();
            holder.personNameSurname = (TextView) convertView.findViewById(R.id.personNameSurname);
            holder.personPhoneNumber = (TextView) convertView.findViewById(R.id.personPhoneNumber);
            convertView.setTag(holder);

        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }

        Person person = persons.get(position);
        if(person != null){
            holder.personNameSurname.setText(person.nameSurname);
            holder.personPhoneNumber.setText("Tel No : "+person.phoneNumber);

        }
        return convertView;
    }



    private static class ViewHolder {
        TextView personNameSurname;
        TextView personPhoneNumber;
    }
}
