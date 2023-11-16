package com.AAACE.RUTidy.dto;

public class Response {
    String message;
    Object object;

    /**
     * Response message + object
     * Please see com.AAACE.RUTidy.constants.ResponseConsants.java
     * for possibe responses
     * @param message
     * @param object
     */
     public Response(String message, Object object) {
        this.message = message;
        this.object = object;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getObject() {
        return this.object;
    }
    
}
