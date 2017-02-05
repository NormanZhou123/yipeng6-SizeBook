package com.example.yipengzhou.thesizebook;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


/*
* This class is the second activity of the app, it allows user to input the info they want to
* save and save the info into a file called FILE.sav. This activity will be activated when the
* add button in the main activity is clicked. It has a save button, when the user click this button
* the info which were input by the user will be saved.
*/

public class SecondActivity extends MainActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        nameTxt = (EditText) findViewById(R.id.body1);
        dateTxt = (EditText) findViewById(R.id.body2);
        neckTxt = (EditText) findViewById(R.id.body3);
        bustTxt = (EditText) findViewById(R.id.body4);
        chestTxt = (EditText) findViewById(R.id.body5);
        waistTxt = (EditText) findViewById(R.id.body6);
        hipTxt = (EditText) findViewById(R.id.body7);
        inseamTxt = (EditText) findViewById(R.id.body8);
        commentTxt = (EditText) findViewById(R.id.body9);
        final Button saveBtn = (Button) findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                addSize(nameTxt.getText().toString(), dateTxt.getText().toString(),
                        neckTxt.getText().toString(), bustTxt.getText().toString(),
                        chestTxt.getText().toString(), waistTxt.getText().toString(),
                        hipTxt.getText().toString(), inseamTxt.getText().toString(),
                        commentTxt.getText().toString());
                populateList();
                Toast.makeText(getApplicationContext(), nameTxt.getText().toString() +
                        " has been added!", Toast.LENGTH_SHORT).show();
                saveInFile();
            }
        });

        nameTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                saveBtn.setEnabled(!nameTxt.getText().toString().trim().isEmpty());
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void populateList() {
        ArrayAdapter<TheSize> adapter= new TheSizeListAdapter();
        sizeListView.setAdapter(adapter);
    }
    private void addSize(String name, String date, String neck, String bust, String chest,
                         String waist, String hip, String inseam, String comment){
        TheSizes.add(new TheSize(name, date, neck, bust, chest, waist, hip, inseam, comment));
    }
}

