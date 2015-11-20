package com.example.andela.notetaker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.andela.notetaker.AppHelper.HelpDialog;
import com.example.andela.notetaker.adapter.NoteAdapter;
import com.example.andela.notetaker.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private LinearLayout takeNote;
    private ImageView headline, search;
    private ListView notes;
    private NoteAdapter noteAdapter;
    private ArrayList<String> timeList;
    private String[] noteArray = {"Kunle's wedding", "Team Leaves StandUp", "Workshop Wednesday", "Design Class"};;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_items);
        headline = (ImageView) findViewById(R.id.view_headline);
        headline.setOnClickListener(this);
        setupDrawerContent(navigationView);
        takeNote = (LinearLayout) findViewById(R.id.take_note);
        takeNote.setOnClickListener(this);
        notes = (ListView) findViewById(R.id.list_note);
        noteAdapter = new NoteAdapter(this, noteArray, getTime());
        notes.setAdapter(noteAdapter);
        notes.setOnItemClickListener(mMessageClickedHandler);
        search = (ImageView) findViewById(R.id.search);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    private void selectDrawerItem(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.trash:
                mDrawerLayout.closeDrawers();
                String[] noteArray = {"Meeting with Bruce"};
                ArrayList<String> timeList = new ArrayList();
                timeList.add("30, Nov 2015   09:00AM");
                noteAdapter = new NoteAdapter(this, noteArray, timeList);
                notes.setAdapter(noteAdapter);
                notes.setOnItemClickListener(mMessageClickedHandler);
                search.setImageDrawable(getResources().getDrawable(R.drawable.ic_delete1));
                break;
            case R.id.help:
                mDrawerLayout.closeDrawers();
                new HelpDialog(this).showHelpToUse();
                break;
            case R.id.notes:
                mDrawerLayout.closeDrawers();
                String[] noteA = {"Kunle's wedding", "Team Leaves StandUp", "Workshop Wednesday", "Design Class"};
                noteAdapter = new NoteAdapter(this, noteA, getTime());
                notes.setAdapter(noteAdapter);
                notes.setOnItemClickListener(mMessageClickedHandler);
                search.setImageDrawable(getResources().getDrawable(R.drawable.ic_search));
                break;
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.view_headline:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.take_note:
                Intent intent = new Intent(this, AddNote.class);
                Bundle bundle = new Bundle();
                bundle.putString("title", "");
                bundle.putString("time", "");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }

    private ArrayList<String> getTime(){
        timeList = new ArrayList();
        timeList.add("30, Nov 2015   09:00PM");
        timeList.add("29, Nov 2015   08:00PM");
        timeList.add("12, Nov 2015   02:PM");
        timeList.add("3, Nov 2015   09:00AM");
        return timeList;
    }

    private ListView.OnItemClickListener mMessageClickedHandler = new ListView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            Intent intent = new Intent(MainActivity.this, AddNote.class);
            Bundle bundle = new Bundle();
            bundle.putString("title", noteArray[position]);
            bundle.putString("time", timeList.get(position));
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
