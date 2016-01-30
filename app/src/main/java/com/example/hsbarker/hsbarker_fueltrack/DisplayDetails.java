package com.example.hsbarker.hsbarker_fueltrack;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
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
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by hsbarker on 1/28/16.
 */

//This is the class that is called when the user wishes to add or edit a fueling entry.
public class DisplayDetails extends AppCompatActivity{
    //For saving data.
    private static final String FILENAME = "file.sav";
    private ArrayList<Fuelings> log = new ArrayList<Fuelings>();

    //In case the user is adding a new entry and not editing an existing one.
    Fuelings Fueling = new Fuelings();

    //Objects to show the user the data.
    private TextView dateIn;
    private EditText stationIn;
    private EditText odReadIn;
    private EditText gradeIn;
    private EditText amountIn;
    private EditText unitCostIn;
    private TextView costIn;
    private String New;

    //http://www.tutorialspoint.com/android/android_datepicker_control.htm
    //A date picker to allow user to edit the date field
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();

        //Check to see if editing existing entry or adding new.
        int position = i.getIntExtra("position", -1);
        if (position == -1){
            New = "Y";
        }
        else {
            Fueling = Log.getInstance().getFueling(position);
            New = "N";
        }

        setContentView(R.layout.activity_fuel_entry);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Apply data to screen for the user.
        dateIn = (TextView) findViewById(R.id.Date);
        stationIn = (EditText) findViewById(R.id.Station);
        odReadIn = (EditText) findViewById(R.id.OdRead);
        gradeIn = (EditText) findViewById(R.id.Grade);
        amountIn = (EditText) findViewById(R.id.Amount);
        unitCostIn = (EditText) findViewById(R.id.UnitCost);
        costIn = (TextView) findViewById(R.id.Cost);

        dateIn.setText(Fueling.getDate());

        //http://www.tutorialspoint.com/android/android_datepicker_control.htm
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        stationIn.setText(Fueling.getStation());
        odReadIn.setText("" + Fueling.getOdread());
        gradeIn.setText(Fueling.getGrade());
        amountIn.setText("" + Fueling.getAmount());
        unitCostIn.setText("" + Fueling.getUnitcost());
        costIn.setText("" + Fueling.getCost());

        //Button that allows user to save changes or a new entry.
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.Save);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);

                String date = dateIn.getText().toString();
                String station = stationIn.getText().toString();
                Double odRead = Double.parseDouble(odReadIn.getText().toString());
                String grade = gradeIn.getText().toString();
                Double amount = Double.parseDouble(amountIn.getText().toString());
                Double unitCost = Double.parseDouble(unitCostIn.getText().toString());

                Fueling.setDate(date);
                Fueling.setStation(station);
                Fueling.setOdread(odRead);
                Fueling.setGrade(grade);
                Fueling.setAmount(amount);
                Fueling.setUnitcost(unitCost);
                Fueling.setCost();
                costIn.setText("" + Fueling.getCost());

                //If the entry is new, add it to the list or else do nothing(the entry is already in the list).
                if (New == "Y"){
                    Log.getInstance().add(Fueling);
                }
                else {
                    ;
                }

                //Save the changes and send the user back to the FuelLog screen.
                saveInFile();
                Context context = view.getContext();
                Intent intent = new Intent(context, FuelLog.class);
                startActivity(intent);
            }
        });
        //Allow user to leave editting/adding page without saving.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        //TODO Auto-generated method stub
        super.onStart();
    }

    //To save list of fuelings for later use.
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

    //http://www.tutorialspoint.com/android/android_datepicker_control.htm
    //Methods for date picker to allow user to change the date.
    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub

            showDate(arg1, arg2+1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        dateIn.setText(new StringBuilder().append(year).append("-")
                .append(month).append("-").append(day));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fuel_log, menu);
        return true;
    }
}
