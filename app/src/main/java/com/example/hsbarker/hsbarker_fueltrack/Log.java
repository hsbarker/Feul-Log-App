package com.example.hsbarker.hsbarker_fueltrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hayden on 16-01-29.
 */
public class Log {
    private static Log instance = new Log();

    private List<Fuelings> oldFuelList = new ArrayList<Fuelings>();

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
        return this.oldFuelList.get(id);
    }

    public List<Fuelings> getOldFuelList(){
        return this.oldFuelList;
    }
}


