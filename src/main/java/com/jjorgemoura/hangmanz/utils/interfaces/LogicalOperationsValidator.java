/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jjorgemoura.hangmanz.utils.interfaces;

/**
 *
 * @author jorge
 */
public interface LogicalOperationsValidator {
     
    /**
     * 
     * @param referenceValue
     * @return 
     */
    public boolean isEqual(Object referenceValue);
    
    /**
     * 
     * @param referenceValue
     * @return 
     */
    public boolean isNotEqual(Object referenceValue);
    
    
    /**
     * 
     * @param referenceValue
     * @return 
     */
    public boolean isGreaterThan(Object referenceValue);
    
    /**
     * 
     * @param referenceValue
     * @return 
     */
    public boolean isLesserThan(Object referenceValue);
    
    /**
     * 
     * @param referenceValue
     * @return 
     */
    public boolean isGreaterOrEqualThan(Object referenceValue);
    
    /**
     * 
     * @param referenceValue
     * @return 
     */
    public boolean isLesserOrEqualThan(Object referenceValue);
    
    /**
     * 
     * @param minValue
     * @param maxValue
     * @return 
     */
    public boolean isBetween(Object minValue, Object maxValue);
   
    
    
}
