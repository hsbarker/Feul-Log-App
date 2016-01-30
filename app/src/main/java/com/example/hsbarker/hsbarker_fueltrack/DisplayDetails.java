package com.example.hsbarker.hsbarker_fueltrack;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
import java.util.Date;

/**
 * Created by hsbarker on 1/28/16.
 */
public class DisplayDetails extends AppCompatActivity{
    private static final String FILENAME = "file.sav";
    private ArrayList<Fuelings> log = new ArrayList<Fuelings>();



    Fuelings Fueling = new Fuelings();
    private EditText dateIn;
    private EditText stationIn;
    private EditText odReadIn;
    private EditText gradeIn;
    private EditText amountIn;
    private EditText unitCostIn;
    private TextView costIn;
    private Boolean New;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        int position = i.getIntExtra("position", -1);
        if (position == -1){
            New = Boolean.TRUE;
        }
        else {
            Fueling = Log.getInstance().getFueling(position);
            New = Boolean.FALSE;
        }

        setContentView(R.layout.activity_fuel_entry);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dateIn = (EditText) findViewById(R.id.Date);
        stationIn = (EditText) findViewById(R.id.Station);
        odReadIn = (EditText) findViewById(R.id.OdRead);
        gradeIn = (EditText) findViewById(R.id.Grade);
        amountIn = (EditText) findViewById(R.id.Amount);
        unitCostIn = (EditText) findViewById(R.id.UnitCost);
        costIn = (TextView) findViewById(R.id.Cost);

        dateIn.setText("" + Fueling.getDate());
        stationIn.setText(Fueling.getStation());
        odReadIn.setText("" + Fueling.getOdread());
        gradeIn.setText(Fueling.getGrade());
        amountIn.setText("" + Fueling.getAmount());
        unitCostIn.setText("" + Fueling.getUnitcost());
        costIn.setText("" + Fueling.getCost());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.Save);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);

                // Intent intent = new Intent(this, .class);
                //EditText dateIn = (EditText) findViewById(R.id.Date);
                String station = stationIn.getText().toString();
                Double odRead = Double.parseDouble(odReadIn.getText().toString());
                String grade = gradeIn.getText().toString();
                Double amount = Double.parseDouble(amountIn.getText().toString());
                Double unitCost = Double.parseDouble(unitCostIn.getText().toString());

                Fueling.setStation(station);
                Fueling.setOdread(odRead);
                Fueling.setGrade(grade);
                Fueling.setAmount(amount);
                Fueling.setUnitcost(unitCost);
                Fueling.setCost();
                if (New){
                    Log.getInstance().add(Fueling);
                }
                else {
                    ;
                }
                saveInFile();

                Snackbar.make(view, "Fueling saved!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        //TODO Auto-generated method stub
        super.onStart();
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

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            log = new ArrayList<Fuelings>();
            Log.getInstance().addOldFuelList(log);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }

    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(Log.getInstance().getOldFuelList(), out);
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
