/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jjorgemoura.hangmanz.utils.interfaces;

/**
 *
 * @author jorge
 */
public interface FormatationValidator {
    
    
    /**
     * 
     * @return 
     */
    public boolean isValid();
    
    /**
     * 
     * @param regex
     * @return 
     */
    public boolean isValid(String regex);
}
