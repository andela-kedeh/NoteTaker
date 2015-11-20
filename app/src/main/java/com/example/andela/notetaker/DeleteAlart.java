package com.example.andela.notetaker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by andela on 11/12/15.
 */
public class DeleteAlart {
    private Activity activity;

    public DeleteAlart(Activity activity) {
        this.activity = activity;
    }

    public void showDeleteWarning() {
        final LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.delete_warning, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity).setView(view);
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
            }
        })
        .setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }).show();

    }
}
