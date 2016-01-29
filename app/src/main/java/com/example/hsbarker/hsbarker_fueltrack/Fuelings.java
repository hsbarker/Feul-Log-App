package com.example.hsbarker.hsbarker_fueltrack;

import java.util.Date;

/**
 * Created by hsbarker on 1/28/16.
 */
public class Fuelings {
    public Date date = new Date(System.currentTimeMillis());
    public String station = "";
    public Double odread = 0.0;
    public String grade = "";
    public Double amount = 0.0;
    public Double unitcost = 0.0;
    public Double cost = 0.0;

//    public Fuelings (){
//        this.station = station;
//        this.odread = odread;
//        this.grade = grade;
//        this.amount = amount;
//        this.unitcost = unitcost;
//        this.cost = cost;
//    }

    public Date getDate()
    {return date;}

    public String getStation()
    {return station;}

    public Number getOdread()
    {return odread;}

    public String getGrade()
    {return grade;}

    public Number getAmount()
    {return amount;}

    public Number getUnitcost()
    {return unitcost;}

    public Number getCost()
    {return cost;}

    public void setDate(Date date)
    {this.date = date;}

    public void setStation(String station)
    {this.station = station;}

    public void setOdread(Double odread)
    {this.odread = odread;}

    public void setGrade(String grade)
    {this.grade = grade;}

    public void setAmount(Double amount)
    {this.amount = amount;}

    public void setUnitcost(Double unitcost)
    {this.unitcost = unitcost;}

    public void setCost(Double cost)
    {this.cost = cost;}

}
