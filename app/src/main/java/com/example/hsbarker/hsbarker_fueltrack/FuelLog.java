package com.example.hsbarker.hsbarker_fueltrack;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


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
import java.lang.reflect.Type;
import java.util.ArrayList;


public class FuelLog extends AppCompatActivity {
    private static final String FILENAME = "file.sav";
    private ListView oldFuelList;
    private TextView Total;

    private ArrayList<Fuelings> log = new ArrayList<Fuelings>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadFromFile();
        setContentView(R.layout.activity_fuel_log);
        oldFuelList = (ListView) findViewById(R.id.oldFuelList);
        Total = (TextView) findViewById(R.id.Total);
        Total.setText("$" + Log.getInstance().getTotal());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.Create);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, DisplayDetails.class);
                startActivity(intent);
            }
        });

        oldFuelList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object listItem = oldFuelList.getItemAtPosition(position);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fuel_log, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<String>(this,
                R.layout.content_fuel_log, Log.getInstance().getOldFuelLog());
        oldFuelList.setAdapter(adapter);
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            //https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html Jan-21 2016
            Type listType = new TypeToken<ArrayList<Fuelings>>() {}.getType();
            log = gson.fromJson(in, listType);
            Log.getInstance().addOldFuelList(log);
            Log.getInstance().addOldFuelLog();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            log = new ArrayList<Fuelings>();
            Log.getInstance().addOldFuelList(log);
            Log.getInstance().addOldFuelLog();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}
