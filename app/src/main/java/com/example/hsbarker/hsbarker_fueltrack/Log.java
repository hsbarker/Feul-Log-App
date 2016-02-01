package com.example.hsbarker.hsbarker_fueltrack;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hayden on 16-01-29.
 */

//Basic singleton idea came from here http://stackoverflow.com/questions/10976212/arraylist-as-global-variable.
//However, many of the functions were created by me.
//There is no clear button because I felt that this was supposed to be a log for corporate use.
//Employees enter the fuelings they do in order for the corporation to check costs.
public class Log {
    private static Log instance = new Log();

    //Create a list to store the fuelings for editing and a list to store the output information for FuelLog.
    private List<Fuelings> oldFuelList = new ArrayList<Fuelings>();
    private List<String> oldFuelLog = new ArrayList<>();

    public static Log getInstance() {
        if(instance == null)
        {
            instance = new Log();
        }
        return instance;
    }

    private Log() {
    }


    public void add(Fuelings Fueling){
        oldFuelList.add(Fueling);
    }

    //Add a new entry from the user.
    public Fuelings getFueling(int id){
        return oldFuelList.get(id);
    }

    //For saving the entries.
    public List<Fuelings> getOldFuelList(){
        return oldFuelList;
    }

    //To populate the editable list when the data is loaded.
    public void addOldFuelList(ArrayList<Fuelings> log) {
        //Always empty to avoid double population.
        oldFuelList.clear();
        oldFuelLog.clear();
        for (int i = 0; i < log.size(); i++){
            oldFuelList.add(log.get(i));
        }
    }

    //To populate the FuelLog view list.
    public void addOldFuelLog(){
        //Always empty to avoid double population.
        oldFuelLog.clear();
        for (int i = 0; i < oldFuelList.size(); i++){
            Fuelings Fueling = oldFuelList.get(i);
            String entry = Fueling.str();
            oldFuelLog.add(entry);
        }
    }

    //To provide the data for the FuelLog view.
    public List<String> getOldFuelLog(){
        return oldFuelLog;
    }

    //To check the size of oldFuelLog.
    public int count(){
        return oldFuelLog.size();
    }

    //To show the total of the costs.
    public double getTotal(){
        double Total = 0.0;
        for (int i = 0; i < oldFuelList.size(); i++){
            Fuelings Fueling = oldFuelList.get(i);
            Total += Fueling.getCost();
        }
        return round(Total, 2);
    }

    //http://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
    //To round the cost to specified decimal places.
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}


