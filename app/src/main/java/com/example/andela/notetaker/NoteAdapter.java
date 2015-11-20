package com.example.andela.notetaker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by andela on 9/28/15.
 */
public class NoteAdapter extends ArrayAdapter<String> {

    private Context context;
    private String[] mTitle;
    private ArrayList<String> mTime;

    public NoteAdapter(Context context, String[] title, ArrayList<String> time) {
        super(context, R.layout.note_list, title);
        this.context = context;
        this.mTitle = title;
        this.mTime = time;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater listLayoutInflater = LayoutInflater.from(context);
            convertView = listLayoutInflater.inflate(R.layout.note_list, null);
        }
        TextView country = (TextView) convertView.findViewById(R.id.title);
        country.setText(mTitle[position]);

        TextView amount = (TextView) convertView.findViewById(R.id.time);
        amount.setText(mTime.get(position));

        return convertView;
    }
}
