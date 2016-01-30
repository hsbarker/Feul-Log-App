package com.example.hsbarker.hsbarker_fueltrack;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.ArrayList;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    //Tested add(), addOldFuelLog(), getOldFuelLog() and count().
    public void testaddOldFuelLog(){
        Fuelings Fueling = new Fuelings();
        Log.getInstance().add(Fueling);
        Log.getInstance().addOldFuelLog();
        int size = Log.getInstance().count();
        assertEquals(Fueling.str(), Log.getInstance().getOldFuelLog().get(size - 1));
    }

    //Tested addOldFuelList() and getOldFuelList().
    public void testaddOldFuelList(){
        ArrayList<Fuelings> log = new ArrayList<Fuelings>();
        Fuelings Fueling = new Fuelings();
        log.add(Fueling);
        Log.getInstance().addOldFuelList(log);
        assertEquals(log,Log.getInstance().getOldFuelList());
    }

    //Tested getFueling().
    public void testgetFueling(){
        ArrayList<Fuelings> log = new ArrayList<Fuelings>();
        Fuelings Fueling = new Fuelings();
        log.add(Fueling);
        Log.getInstance().addOldFuelList(log);
        assertEquals(log.get(0),Log.getInstance().getFueling(0));
    }

    //Tested getTotal()
    public void testgetTotal(){
        ArrayList<Fuelings> log = new ArrayList<Fuelings>();
        Fuelings Fueling = new Fuelings();
        log.add(Fueling);
        Log.getInstance().addOldFuelList(log);
        assertEquals(0.0,Log.getInstance().getTotal());
    }

    //Tested round()
    public void testround(){
        Double value = 2.89703495304;
        assertEquals(2.90,Log.getInstance().round(value, 2));
    }

    //Tested some get and set methods as well as setCost().
    public void testsetCost(){
        Fuelings Fueling = new Fuelings();
        Fueling.setUnitcost(100.1);
        Fueling.setAmount(30.73);
        Fueling.setCost();
        assertEquals(30.76,Fueling.getCost());
    }

    //Tested to make sure the correct string was being created with str().
    public void teststr(){
        Fuelings Fueling = new Fuelings();
        assertEquals("Date : " + Fueling.getDate() + "\n Station : " + Fueling.getStation() + "\n Odometer Reading(KM) : " + Fueling.getOdread() + "\n Grade : " + Fueling.getGrade() + "\n Litres : " + Fueling.getAmount() + "\n Cents/Litres : " + Fueling.getUnitcost() + "\n Cost($) : " + Fueling.getCost(), Fueling.str());
    }

}