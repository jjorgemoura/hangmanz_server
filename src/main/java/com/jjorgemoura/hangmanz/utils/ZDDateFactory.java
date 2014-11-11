/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jjorgemoura.hangmanz.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author jorge
 */
public class ZDDateFactory {
    
    //-----------Constructor---------
    public ZDDateFactory() {
    
    }
    
    
    //-----------FACTORY METHODS---------
    /**
     * Create a new instance of IZDate with the current dateTime.
     * 
     * @return A instance of IZdate.
     */
    public static ZDDate newIZDate() {
    
        
        return new ZDDate();
    }
    
    
    /**
     * 
     * Create a new instance of IZDate based on a specific date.
     * 
     * @param date A instance of java.util.Date.
     * @return A instance of IZdate.
     */
    public static ZDDate newIZDateFromDate(Date date) {
    
        
        return new ZDDate(date);
    }
    
    /**
     * Create a new instance of IZDate based on a specific date.
     * 
     * @param date A string representation of a Date.
     * @return A instance of IZdate.
     */
    public static ZDDate newIZDateFromDate(String date) {
    
        
        return new ZDDate(date);
    }
    
    /**
     * Create a new instance of IZDate based on a specific date represented by the values of year, month and day.
     * 
     * @param year An integer representation of a year.
     * @param month An integer representation of a month.
     * @param day An integer representation of a day.
     * @return A instance of IZdate.
     */
    public static ZDDate newIZDate(int year, int month, int day) {
            
        GregorianCalendar gc;

        gc = new GregorianCalendar(year, month - 1, day);
        
        return new ZDDate(gc.getTime());
    }
    
    
    /**
     * 
     * Create a new instance of IZDate based on a specific date added of some seconds.
     * 
     * @param date The date.
     * @param seconds The seconds to be added to the date.
     * @return A instance of IZdate.
     */
    public static ZDDate newIZDateByAddSecondsFromDate(Date date, int seconds) {
    
        
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        
        gc.add(Calendar.SECOND, seconds);
                
        return new ZDDate(gc.getTime());
    }
    
    /**
     * Create a new instance of IZDate based on a specific date added of some minutes.
     * 
     * @param date The date.
     * @param minutes The minutes to be added to the date.
     * @return A instance of IZdate.
     */
    public static ZDDate newIZDateByAddMinutesFromDate(Date date, int minutes) {
    
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        
        gc.add(Calendar.MINUTE, minutes);
                
        return new ZDDate(gc.getTime());
    }
    
    /**
     * Create a new instance of IZDate based on a specific date added of some hours.
     * 
     * @param date The date.
     * @param hours The hours to be added to the date.
     * @return A instance of IZdate.
     */
    public static ZDDate newIZDateByAddHoursFromDate(Date date, int hours) {
    
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        
        gc.add(Calendar.HOUR, hours);
                
        return new ZDDate(gc.getTime());
    }
    
    
    /**
     * Create a new instance of IZDate based on a specific date added of some days.
     * 
     * @param date The date.
     * @param days The days to be added to the date.
     * @return A instance of IZdate.
     */
    public static ZDDate newIZDateByAddDaysFromDate(Date date, int days) {
    
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        
        gc.add(Calendar.DAY_OF_MONTH, days);
                
        return new ZDDate(gc.getTime());
    }
    
    /**
     * Create a new instance of IZDate based on a specific date added of some months.
     * 
     * @param date The date.
     * @param months The months to be added to the date.
     * @return A instance of IZdate.
     */
    public static ZDDate newIZDateByAddMonthsFromDate(Date date, int months) {
    
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        
        gc.add(Calendar.MONTH, months);
                
        return new ZDDate(gc.getTime());
    }
    
    /**
     * Create a new instance of IZDate based on a specific date added of some years.
     * 
     * @param date The date.
     * @param years The years to be added to the date.
     * @return A instance of IZdate.
     */
    public static ZDDate newIZDateByAddYearsFromDate(Date date, int years) {
    
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        
        gc.add(Calendar.YEAR, years);
                
        return new ZDDate(gc.getTime());
    }
    
}
