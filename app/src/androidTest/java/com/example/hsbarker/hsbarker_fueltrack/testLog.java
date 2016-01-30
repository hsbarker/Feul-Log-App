package com.example.hsbarker.hsbarker_fueltrack;

/**
 * Created by hsbarker on 1/29/16.
 */

import android.app.Application;

public class testLog {

    public void testaddOldFuelLog(){
        Fuelings Fueling = new Fuelings();
        Log.getInstance().add(Fueling);
        Log.getInstance().addOldFuelLog();
    }
}
