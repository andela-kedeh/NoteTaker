package com.example.andela.notetaker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by andela on 11/12/15.
 */
public class SetSavingTime {
    private Activity activity;

    public SetSavingTime(Activity activity) {
        this.activity = activity;
    }

    public void show() {
        final LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.set_time, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity).setView(view);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }).show();

    }
}
