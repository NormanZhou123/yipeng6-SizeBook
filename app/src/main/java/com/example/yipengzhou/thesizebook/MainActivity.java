package com.example.yipengzhou.thesizebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

public class MainActivity extends AppCompatActivity {

    EditText nameTxt, dateTxt, neckTxt, bustTxt, chestTxt, waistTxt,
            hipTxt, inseamTxt, commentTxt;
    public ListView sizeListView;
    public List<TheSize> TheSizes = new ArrayList<>();
    public static final String FILENAME = "file.sav";
    int LongClickedItemIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button add = (Button) findViewById(R.id.add);

        sizeListView = (ListView) findViewById(R.id.listView);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
        registerForContextMenu(sizeListView);
        sizeListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                LongClickedItemIndex=position;
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu");
        menu.add(0, v.getId(), 0, "Edit");
        menu.add(0, v.getId(), 0, "Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle().equals("Edit")) {
            Toast.makeText(this, "Editing", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this, EditActivity.class);
            i.putExtra("MyClass", TheSizes.get(LongClickedItemIndex));
            startActivity(i);
        }
        else if (item.getTitle() == "Delete") {
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
            TheSizes.remove(LongClickedItemIndex);
            ArrayAdapter<TheSize> adapter= new TheSizeListAdapter();
            sizeListView.setAdapter(adapter);
            saveInFile();
        }
        else {
            return false;
        }
        return true;
    }

    public class TheSizeListAdapter extends ArrayAdapter<TheSize>{
        public TheSizeListAdapter(){
            super (MainActivity.this, R.layout.list_item, TheSizes);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent){
            if (view == null)
                view = getLayoutInflater().inflate(R.layout.list_item,parent,false);

            TheSize currentSize = TheSizes.get(position);

            TextView count = (TextView) view.findViewById(R.id.count);

            TextView name = (TextView) view.findViewById(R.id.sName);
            name.setText(currentSize.getName());
            TextView date = (TextView) view.findViewById(R.id.sDate);
            date.setText(currentSize.getDate());
            TextView neck = (TextView) view.findViewById(R.id.sNeck);
            neck.setText(currentSize.getNeck());
            TextView bust = (TextView) view.findViewById(R.id.sBust);
            bust.setText(currentSize.getBust());
            TextView chest = (TextView) view.findViewById(R.id.sChest);
            chest.setText(currentSize.getChest());
            TextView waist = (TextView) view.findViewById(R.id.sWaist);
            waist.setText(currentSize.getWaist());
            TextView hip = (TextView) view.findViewById(R.id.sHip);
            hip.setText(currentSize.getHip());
            TextView inseam = (TextView) view.findViewById(R.id.sInseam);
            inseam.setText(currentSize.getInseam());
            TextView comment = (TextView) view.findViewById(R.id.sComment);
            comment.setText(currentSize.getComment());
            int p1=position+1;
            count.setText("Size number" + p1);
            return view;
        }
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        ArrayAdapter<TheSize> adapter= new TheSizeListAdapter();
        sizeListView.setAdapter(adapter);

    }


    public void loadFromFile() {

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();
            TheSizes = gson.fromJson(in, new TypeToken<ArrayList<TheSize>>(){}.getType());

            fis.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            TheSizes = new ArrayList<TheSize>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    public void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();

            gson.toJson(TheSizes, out);

            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}