package com.example.andela.notetaker.AppHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.andela.notetaker.R;

/**
 * Created by andela on 11/12/15.
 */
public class HelpDialog {
    private Activity activity;

    public HelpDialog(Activity activity) {
        this.activity = activity;
    }

    public void showHelpToUse() {
        final LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.how_to_use, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity).setView(view);
        builder.setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        }).show();

    }
}
