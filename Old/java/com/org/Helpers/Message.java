package com.org.Helpers;

public class Message {
    public Boolean status;
    public String content;
    public String category;

    public Message(){
        this.status = null;
        this.content = null;
        this.category = null;
    }

    public Message(Boolean status, String content, String category){
        this.status = status;
        this.content = content;
        this.category = category;
    }
}
