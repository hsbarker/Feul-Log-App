package com.example.hsbarker.hsbarker_fueltrack;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hayden on 16-01-29.
 */
public class Log {
    private static Log instance = new Log();

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

    public Fuelings getFueling(int id){
        return oldFuelList.get(id);
    }

    public List<Fuelings> getOldFuelList(){
        return oldFuelList;
    }

    public void addOldFuelList(ArrayList<Fuelings> log) {
        oldFuelList.clear();
        oldFuelLog.clear();
        for (int i = 0; i < log.size(); i++){
            oldFuelList.add(log.get(i));
        }
    }

    public void addOldFuelLog(){
        oldFuelLog.clear();
        for (int i = 0; i < oldFuelList.size(); i++){
            Fuelings Fueling = oldFuelList.get(i);
            String entry = Fueling.str();
            oldFuelLog.add(entry);
        }
    }

    public List<String> getOldFuelLog(){
        return oldFuelLog;
    }

    public int count(){
        return oldFuelLog.size();
    }

    public double getTotal(){
        double Total = 0.0;
        for (int i = 0; i < oldFuelList.size(); i++){
            Fuelings Fueling = oldFuelList.get(i);
            Total += Fueling.getCost();
        }
        return round(Total, 2);
    }

    //http://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}


