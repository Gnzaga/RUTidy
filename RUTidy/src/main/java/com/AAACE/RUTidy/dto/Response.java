package com.AAACE.RUTidy.dto;

public class Response {
    String message;
    Object object;

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
