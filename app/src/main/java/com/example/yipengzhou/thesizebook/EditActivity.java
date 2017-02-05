package com.example.yipengzhou.thesizebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/*
* This class is the edit activity of the app. It allows user to edit the info of the items in the
* list view. Part of the code of this class is the same with the SecondActivity, they have the
* same layout. But when the user click edit to activate this activity, the info in the item will
* be shown in the text box so that the user can view the details of the item and make change to
* it. */

public class EditActivity extends MainActivity {


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Intent intent = getIntent();
        final int current_pos = intent.getIntExtra("MyClass", 0);

        nameTxt = (EditText) findViewById(R.id.ebody1);
        dateTxt = (EditText) findViewById(R.id.ebody2);
        neckTxt = (EditText) findViewById(R.id.ebody3);
        bustTxt = (EditText) findViewById(R.id.ebody4);
        chestTxt = (EditText) findViewById(R.id.ebody5);
        waistTxt = (EditText) findViewById(R.id.ebody6);
        hipTxt = (EditText) findViewById(R.id.ebody7);
        inseamTxt = (EditText) findViewById(R.id.ebody8);
        commentTxt = (EditText) findViewById(R.id.ebody9);
        final Button saveBtn = (Button) findViewById(R.id.esaveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String name = nameTxt.getText().toString();
                String date = dateTxt.getText().toString();
                String neck = neckTxt.getText().toString();
                String bust = bustTxt.getText().toString();
                String chest = chestTxt.getText().toString();
                String waist = waistTxt.getText().toString();
                String hip = hipTxt.getText().toString();
                String inseam = inseamTxt.getText().toString();
                String comment = commentTxt.getText().toString();
                TheSizes.set(current_pos, new TheSize(name, date, neck, bust, chest,
                        waist, hip, inseam, comment));
                Toast.makeText(getApplicationContext(), nameTxt.getText().toString() +
                        " has been changed!", Toast.LENGTH_SHORT).show();
                saveInFile();
            }
        });
    }

    protected void onStart(){
        super.onStart();
        Intent intent = getIntent();
        int pos = intent.getIntExtra("MyClass",0);
        nameTxt.setText(TheSizes.get(pos).getName());
        dateTxt.setText(TheSizes.get(pos).getDate());
        neckTxt.setText(TheSizes.get(pos).getNeck());
        bustTxt.setText(TheSizes.get(pos).getBust());
        chestTxt.setText(TheSizes.get(pos).getChest());
        waistTxt.setText(TheSizes.get(pos).getWaist());
        hipTxt.setText(TheSizes.get(pos).getHip());
        inseamTxt.setText(TheSizes.get(pos).getInseam());
        commentTxt.setText(TheSizes.get(pos).getComment());

        ArrayAdapter<TheSize> adapter= new TheSizeListAdapter();
        sizeListView.setAdapter(adapter);
    }
}

