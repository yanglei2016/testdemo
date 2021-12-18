package com.example.testdemo.common.exception;

/**
 * @
 * @Date: 2021-12-10 21:48
 */
public class ArgumentInvalidResult {
    private String field;
    private Object rejectedValue;
    private String defaultMessage;
    
    public String getField() {
        return field;
    }
    
    public void setField(String field) {
        this.field = field;
    }
    
    public Object getRejectedValue() {
        return rejectedValue;
    }
    
    public void setRejectedValue(Object rejectedValue) {
        this.rejectedValue = rejectedValue;
    }
    
    public String getDefaultMessage() {
        return defaultMessage;
    }
    
    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }
    
}
