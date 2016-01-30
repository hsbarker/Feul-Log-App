package com.example.hsbarker.hsbarker_fueltrack;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

/**
 * Created by hsbarker on 1/28/16.
 */
public class Fuelings {
    public Date date = new Date(System.currentTimeMillis());
    public String station = "";
    public Double odread = 0.0;
    public String grade = "";
    public Double amount = 0.000;
    public Double unitcost = 0.0;
    public Double cost = 0.00;


    //http://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-places
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();}

    public Date getDate()
    {return date;}

    public String getStation()
    {return station;}

    public Double getOdread()
    {return odread;}

    public String getGrade()
    {return grade;}

    public Double getAmount()
    {return amount;}

    public Double getUnitcost()
    {return unitcost;}

    public Double getCost()
    {return cost;}

    public void setDate(Date date)
    {this.date = date;}

    public void setStation(String station)
    {this.station = station;}

    public void setOdread(Double odread)
    {this.odread = round(odread,1);}

    public void setGrade(String grade)
    {this.grade = grade;}

    public void setAmount(Double amount)
    {this.amount = round(amount,3);}

    public void setUnitcost(Double unitcost)
    {this.unitcost = round(unitcost,1);}

    public void setCost()
    {
        double price = unitcost/100;
        this.cost = round(price * amount,2);}

    public String str(){
        String entry = "Date : " + this.getDate() + "\n Station : " + this.getStation() + "\n Odometer Reading(KM) : " + this.getOdread() + "\n Grade : " + this.getGrade() + "\n Litres : " + this.getAmount() + "\n Cents/Litres : " + this.getUnitcost() + "\n Cost($) : " + this.getCost();
        return entry;
    }

}
