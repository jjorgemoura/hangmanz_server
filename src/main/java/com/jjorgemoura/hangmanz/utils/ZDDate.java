/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jjorgemoura.hangmanz.utils;

import com.jjorgemoura.hangmanz.utils.interfaces.ExistenceValidator;
import com.jjorgemoura.hangmanz.utils.interfaces.FormatationValidator;
import com.jjorgemoura.hangmanz.utils.interfaces.LogicalOperationsValidator;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

/**
 *
 * @author jorge
 */
public class ZDDate extends GregorianCalendar implements LogicalOperationsValidator, ExistenceValidator, FormatationValidator {
   
    //private static final Logger LOG = LoggerFactory.getLogger(IZDate.class);
    
    /**
     * The format used by default (yyyy-MM-dd)
     */
    public static final String FORMAT_ONLY_DATE = "yyyy-MM-dd";
    
    /**
     * The format used by default (yyyy-MM-dd HH)
     */
    public static final String FORMAT_DATE_HOUR = "yyyy-MM-dd HH";
    
    /**
     * The format used by default (yyyy-MM-dd HH:mm)
     */
    public static final String FORMAT_DATE_HOUR_MIN = "yyyy-MM-dd HH:mm";
    
    /**
     * The format used by default (yyyy-MM-dd HH:mm:ss)
     */
    public static final String FORMAT_DATE_HOUR_MIN_SS = "yyyy-MM-dd HH:mm:ss.SS";
    
    /**
     * The format used by default (yyyy-MM-dd HH:mm:ss.mm)
     */
    public static final String FORMAT_DATE_HOUR_MIN_SS_MILI = "yyyy-MM-dd HH:mm:ss.SS";
    
    /**
     * 
     */
    private String defaultFormat = FORMAT_ONLY_DATE;

    

//--------------------------CONSTRUTORES------------------------------------
    /**
     * Default constructor.
     */
    public ZDDate() {

        super();
    }

    /**
     * Constructor with init parameter.
     * 
     * @param timeInMillis The time in milisecs.
     */
    public ZDDate(long timeInMillis) {

        super();
        this.setTimeInMillis(timeInMillis);
    }

    /**
     * Constructor with init parameter.
     * 
     * @param date The java.util.Date
     */
    public ZDDate(Date date) {

        super();
        this.setTime(date);
    }

    /**
     * Constructor with init parameter.
     * 
     * @param inDate The date as String.
     */
    public ZDDate(String inDate) {

        //this(inDate, IZDate.FORMAT_ONLY_DATE);
        
        
        
        //Alternativa
        super();
        String f = ZDDate.FORMAT_ONLY_DATE;
        
        
        try {
            
            String[] split = inDate.split("-");
        
            
            if(split[0].length() == 2 && split[1].length() == 2 && split[2].length() == 4) {
            
                f = "dd-MM-yyyy";
            }
        }
        catch(Exception ex) {
        
            throw new RuntimeException("Error parsing String to IZDate");
        }
        
        
        this.defaultFormat = f;

        if (inDate == null) {
            throw new InvalidParameterException("The date could not be null");
        }

        //set the format to use as a constructor argument
        SimpleDateFormat dateFormat = new SimpleDateFormat(this.defaultFormat);

        if (inDate.trim().length() != dateFormat.toPattern().length()) {
            throw new InvalidParameterException("The date and format have diferent sizes");
        }

        dateFormat.setLenient(false);

        try {

            //parse the inDate parameter
            Date parsedDate = dateFormat.parse(inDate.trim());
            this.setTime(parsedDate);

        } catch (ParseException pe) {
            throw new InvalidParameterException("Invalid date");
        }
    }

    /**
     * Constructor with init parameter.
     * 
     * @param inDate The date as String.
     * @param defaultDateFormat The date format.
     */
    public ZDDate(String inDate, String defaultDateFormat) {

        super();
        this.defaultFormat = defaultDateFormat;

        if (inDate == null) {
            throw new InvalidParameterException("The date could not be null");
        }

        //set the format to use as a constructor argument
        SimpleDateFormat dateFormat = new SimpleDateFormat(this.defaultFormat);

        if (inDate.trim().length() != dateFormat.toPattern().length()) {
            throw new InvalidParameterException("The date and format have diferent sizes");
        }

        dateFormat.setLenient(false);

        try {

            //parse the inDate parameter
            Date parsedDate = dateFormat.parse(inDate.trim());
            this.setTime(parsedDate);

        } catch (ParseException pe) {
            throw new InvalidParameterException("Invalid date");
        }
    }

    //--------------Public Methods ACTIONS-------------------------
    /**
     * The method add some minutes to this IZDate instance.
     * 
     * @param minutes 
     */
    public void addMinutes(int minutes) {

        this.add(Calendar.MINUTE, minutes);
    }
    
    /**
     * The method add some hours to this IZDate instance.
     * 
     * @param hours 
     */
    public void addHours(int hours) {

        this.add(Calendar.HOUR, hours);
    }

    /**
     * The method add some days to this IZDate instance.
     * 
     * @param days 
     */
    public void addDays(int days) {

        this.add(Calendar.DAY_OF_MONTH, days);
    }

    /**
     * The method add some months to this IZDate instance.
     * 
     * @param months 
     */
    public void addMonths(int months) {

        this.add(Calendar.MONDAY, months);
    }

    /**
     * The method add some years to this IZDate instance.
     * 
     * @param years 
     */
    public void addYears(int years) {

        this.add(Calendar.YEAR, years);
    }

    /**
     * The method subtract some minutes to this IZDate instance.
     * 
     * @param days 
     */
    public void subtractDays(int days) {

        this.subtractDays(days);
    }

    
    
    
    
    
    //--------------Public Methods GETS-------------------------
    /**
     * Return this date represented as String.
     * 
     * @return The date as string.
     */
    @Override
    public String toString() {

        return this.toString(this.defaultFormat);
    }

    /**
     * Return this date represented as String with some specific date format.
     * 
     * @param format The format. This must be one of the formats available in this class (static fields).
     * @return The date as String.
     */
    public String toString(String format) {


        SimpleDateFormat df = new SimpleDateFormat(format);


        String s = df.format(this.getTime());

        return s;
    }

    /**
     * This method return the age (difference years) between this date and the date passed as parameter.
     * @param untilIZDate the date to test the age.
     * @return The age between this date and the parameter date.
     */
    public int ageUntil(ZDDate untilIZDate) {

        
        int result = untilIZDate.get(Calendar.YEAR) - this.get(Calendar.YEAR);

        if ((this.get(Calendar.MONTH) > untilIZDate.get(Calendar.MONTH)) || (this.get(Calendar.MONTH) == untilIZDate.get(Calendar.MONTH) && this.get(Calendar.DAY_OF_MONTH) > untilIZDate.get(Calendar.DAY_OF_MONTH))) {

            result--;
        }
        
//        if (untilIZDate.get(Calendar.MONTH) > (this.get(Calendar.MONTH)) || (untilIZDate.get(Calendar.MONTH) == this.get(Calendar.MONTH) && untilIZDate.get(Calendar.DAY_OF_MONTH) > this.get(Calendar.DAY_OF_MONTH))) {
//
//            result--;
//        }
        
        
        if(result < 0) {
        
            return 0;
        }
        

        //LOG.info("AGE: " + String.valueOf(res));
        return result;
    }

    /**
     * This method calculate the days between dates
     * @param date  the referece date.
     * @return The number of days.
     */
    public long diffDaysWithIZDate(ZDDate date) {

        long result = 0;

        long milidiff = this.getTimeInMillis() - date.getTimeInMillis();
        result = milidiff / 86400000;

        return result;

    }
    
    /**
     * This method calculate the days between dates
     * @param date  the referece date.
     * @param onlyDate
     * @return The number of days.
     */
    public long diffDaysWithIZDate(ZDDate date, boolean onlyDate) {

        long result = 0;

        
        if(onlyDate) {
            
        
            GregorianCalendar gc2 = new GregorianCalendar();
            gc2.set(Calendar.HOUR_OF_DAY, 0);
            gc2.set(Calendar.MINUTE, 0);
            gc2.set(Calendar.SECOND, 0);
            gc2.set(Calendar.DAY_OF_MONTH, this.get(Calendar.DAY_OF_MONTH));
            gc2.set(Calendar.YEAR, this.get(Calendar.YEAR));
            gc2.set(Calendar.MONTH, this.get(Calendar.MONTH));
        
        
            GregorianCalendar gcX = new GregorianCalendar();
            gcX.set(Calendar.HOUR_OF_DAY, 0);
            gcX.set(Calendar.MINUTE, 0);
            gcX.set(Calendar.SECOND, 0);
            gcX.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH));
            gcX.set(Calendar.YEAR, date.get(Calendar.YEAR));
            gcX.set(Calendar.MONTH, date.get(Calendar.MONTH));
               
                

            long milidiffXXX = gcX.getTimeInMillis() - gc2.getTimeInMillis();
            result = milidiffXXX / 86400000;
    
        }
        else {
        
            result = this.diffDaysWithIZDate(date);
        }
        

        return result;

    }
    

    /**
     * This method test if the both dates are from same day.
     * 
     * @param date the reference date.
     * @return A boolean indicating if both dates are from the same day.
     */
    public boolean isSameDayAs(ZDDate date) {

        if (this.get(Calendar.YEAR) == date.get(Calendar.YEAR) && this.get(Calendar.MONTH) == date.get(Calendar.MONTH) && this.get(Calendar.DAY_OF_MONTH) == date.get(Calendar.DAY_OF_MONTH)) {

            return true;
        } else {

            return false;
        }
    }

    /**
     * This method test if this date is greater than the parameter date.
     * 
     * @param referenceDate the reference date.
     * @return A boolean indicating if this instance date is greater than the reference date.
     */
    public boolean isGreaterThan(ZDDate referenceDate) {


        if (this.after(referenceDate)) {

            return true;
        } else {

            return false;
        }
    }
    
    /**
     * This method test if this date is greater or equal than the parameter date.
     * 
     * @param referenceDate the reference date.
     * @return A boolean indicating if this instance date is greater or equal than the reference date.
     */
    public boolean isGreaterOrEqualThan(ZDDate referenceDate) {
        
        int compareToMills = this.compareTo(referenceDate);
        

        if (compareToMills >= 0) {

            return true;
        } else {

            return false;
        }
    }
    

    /**
     * This method test if this date is, at least, 1 day greater than the parameter date. Doesn't matter the time.
     * 
     * @param referenceDate the reference date.
     * @return A boolean indicating if this instance date is 1 day greater than the reference date.
     */
    public boolean isGreaterThanReferenceDayBy1Day(ZDDate referenceDate) {

        //Primeiro testamos se o referenceDay e maior ou nao
        boolean result = false;

        if (this.isGreaterThan(referenceDate)) {

            //Vamos agora gerar uma nova data temporaria que e igual a data de referencia esta instancia mas com mais um dia
            //Depois vamos testar que sao do mesmo dia

            ZDDate refDatePlus1 = ZDDateFactory.newIZDateByAddDaysFromDate(referenceDate.getTime(), 1);

            //ver se sao do mesmo dia
            if (this.isGreaterOrEqualThanReferenceDateByOnlyDate(refDatePlus1)) {

                result = true;
            } else {

                result = false;
            }

        } else {

            //nem seque e maior que a data de referencia
            result = false;
        }


        return result;
    }

    /**
     * This method test if this date is 1 day (only) greater (i.e. is the next day) than the parameter date. Doesn't matter the time.
     * 
     * @param referenceDate the reference date.
     * @return A boolean indicating if this instance date is 1 day greater than the reference date.
     */
    public boolean isNextDay(ZDDate referenceDate) {

        //Primeiro testamos se o referenceDay e maior ou nao
        boolean result = false;

        if (this.isGreaterThan(referenceDate)) {

            //Vamos agora gerar uma nova data temporaria que e igual a data de referencia esta instancia mas com mais um dia
            //Depois vamos testar que sao do mesmo dia

            ZDDate refDatePlus1 = ZDDateFactory.newIZDateByAddDaysFromDate(referenceDate.getTime(), 1);

            //ver se sao do mesmo dia
            if (this.isSameDayAs(refDatePlus1)) {

                result = true;
            } else {

                result = false;
            }

        } else {

            //nem seque e maior que a data de referencia
            result = false;
        }



        return result;
    }
    
    /**
     * This method test if this date is greater than the parameter date, but only considering the date.
     * 
     * @param referenceDate the reference date.
     * @return A boolean indicating if this instance date is greater than the reference date, only considering the date.
     */
    public boolean isGreaterThanReferenceDateByOnlyDate(ZDDate referenceDate) {

        boolean result = false;

        if (this.isGreaterThan(referenceDate)) {

            //OK, e maior do que a data de referencia.
            //Basta ver agora se e do mesmo dia ou nao

            if (this.isSameDayAs(referenceDate)) {

                //afinal e do mesmo dia
                result = false;
            } else {

                //nao e mesmo dia
                result = true;
            }
        } else {

            //nao e maior. Logo....
            result = false;
        }


        return result;
    }

    /**
     * This method test if this date is greater or equal than the parameter date, but only considering the date.
     * 
     * @param referenceDate the reference date.
     * @return A boolean indicating if this instance date is greater or equal than the reference date, only considering the date.
     */
    public boolean isGreaterOrEqualThanReferenceDateByOnlyDate(ZDDate referenceDate) {

        boolean result = false;

        if (this.isGreaterThan(referenceDate)) {

            //Ok, e maior
            result = true;
        } else {

            //nao e maior. Mas se for do mesmo dia, entao sao iguais
            if (this.isSameDayAs(referenceDate)) {

                //afinal e do mesmo dia
                result = true;
            } else {

                //nao e mesmo dia
                result = false;
            }
        }


        return result;
    }

    
    
    
    
    //-----------------PUBLIC Methods AUX----------------------
    /**
     * 
     * @param defaultFormat The date format.
     */
    public void setDefaultFormat(String defaultFormat) {

        this.defaultFormat = defaultFormat;
    }

    /**
     * 
     * @return The date format.
     */
    public String getDefaultFormat() {

        return defaultFormat;
    }
    
    /**
     * 
     * @return The date format.
     */
    public int getMonth() {

        return this.get(Calendar.MONTH) + 1;
    }
    
    public int getYear() {

        return this.get(Calendar.YEAR);
    }
    
    public int getDayOfMonth() {

        return this.get(Calendar.DAY_OF_MONTH);
    }
    
    
    
    
    /**
     * This method is the same as IZDate.isSameDayAs(obj).
     * 
     * @param referenceValue
     * @return 
     */
    @Override
    public boolean isEqual(Object referenceValue) {
         boolean result = false;
         
         if (referenceValue instanceof ZDDate){
            result = this.isSameDayAs((ZDDate)referenceValue);
         }
        return result;
    }

    /**
     * This method is the inverse of IZDate.isSameDayAs(obj).
     * 
     * 
     * @param referenceValue
     * @return 
     */
    @Override
    public boolean isNotEqual(Object referenceValue) {
       boolean result = false;
         
         if (referenceValue instanceof ZDDate){
            result = !(this.isSameDayAs((ZDDate)referenceValue));
         }
        return result;
    }

    
    /**
     * This method is the same as IZDate.isGreaterThan(obj).
     * 
     * @param referenceValue
     * @return 
     */
    @Override
    public boolean isGreaterThan(Object referenceValue) {
        boolean result = false;
       
        
        if (referenceValue instanceof ZDDate){
            result = isGreaterThan((ZDDate)referenceValue);       
        }
        return result;
        
        
    }
    
    
    /**
     * This method is the same as IZDate.isGreaterOrEqualThan(obj).
     * 
     * @param referenceValue
     * @return 
     */
    @Override
    public boolean isGreaterOrEqualThan(Object referenceValue) {
        boolean result = false;
       
        if (referenceValue instanceof ZDDate){
            
            result = isGreaterOrEqualThan((ZDDate)referenceValue);       
        }
        return result;
         
    }
    
    
    
    /**
     * This method is the inverse of IZDate.isGreaterThan(obj).
     * 
     * @param referenceValue
     * @return 
     */
    @Override
    public boolean isLesserThan(Object referenceValue) {
        boolean result = false;
       
        if (referenceValue instanceof ZDDate){
            result = !(isGreaterThan((ZDDate)referenceValue));       
        }
        return result;
        
    }

   
    /**
     * This method is the inverse of IZDate.isLesserOrEqualThan(obj).
     * 
     * @param referenceValue
     * @return 
     */
    @Override
    public boolean isLesserOrEqualThan(Object referenceValue) {
        boolean result = false;
       
        if (referenceValue instanceof ZDDate){
            result = !(isGreaterOrEqualThan((ZDDate)referenceValue));       
        }
        return result;
    }

    
    @Override
    public boolean isBetween(Object minValue, Object maxValue) {
        
        return (isGreaterOrEqualThan(minValue) && isLesserOrEqualThan(maxValue));
    }

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public boolean isValid(String regex) {
        boolean result;
        
        Pattern pt = Pattern.compile(regex);
        result = pt.matcher(this.toString()).matches();
        
        
        return result;
    }
}
