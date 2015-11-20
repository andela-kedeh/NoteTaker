package com.example.andela.notetaker;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Field;

public class AddNote extends AppCompatActivity implements View.OnClickListener,  PopupMenu.OnMenuItemClickListener{

    private ImageView backHome, recordedMic;
    private ImageView settings, timeIcon;
    private TextView title, playRecord;
    private TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        init();
    }

    private void init() {
        backHome = (ImageView) findViewById(R.id.back_home);
        backHome.setOnClickListener(this);
        settings = (ImageView) findViewById(R.id.settings);
        settings.setOnClickListener(this);
        timeIcon = (ImageView) findViewById(R.id.time_icon);
        recordedMic = (ImageView) findViewById(R.id.mic_icon);
        playRecord = (TextView) findViewById(R.id.play_record);
        title = (TextView) findViewById(R.id.title);
        time = (TextView) findViewById(R.id.time);
        if(!getIntent().getExtras().getString("title").isEmpty()){
            recordedMic.setVisibility(View.VISIBLE);
            playRecord.setVisibility(View.VISIBLE);
            time.setVisibility(View.VISIBLE);
            timeIcon.setVisibility(View.VISIBLE);
        }
        title.setText(getIntent().getExtras().getString("title"));
        time.setText(getIntent().getExtras().getString("time"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.settings:
                showMenu(v);
                break;
        }
    }



    public void showMenu(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.inflate(R.menu.menu_main);
        popup.setOnMenuItemClickListener(this);

        // Force icons to show
        Object menuHelper;
        Class[] argTypes;
        try {
            Field fMenuHelper = PopupMenu.class.getDeclaredField("mPopup");
            fMenuHelper.setAccessible(true);
            menuHelper = fMenuHelper.get(popup);
            argTypes = new Class[] { boolean.class };
            menuHelper.getClass().getDeclaredMethod("setForceShowIcon", argTypes).invoke(menuHelper, true);
        } catch (Exception e) {
            popup.show();
            return;
        }
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.add:
                new RecordAlert(this).show();
                break;
            case R.id.share:
                break;
            case R.id.delete:
                new DeleteAlart(this).showDeleteWarning();
                break;
            case R.id.settings:
                new SetSavingTime(this).show();
                break;
        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
