package com.example.shoppingapp.onlineshopping.Pojo;

import java.io.Serializable;

/**
 * Created by shoppingapp on 10/7/16.
 */

public class FeedbackObject implements Serializable {
    String id,name,email,phone,message;

    public FeedbackObject() {
    }

    public FeedbackObject(String name, String email, String phone, String message) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.message = message;
    }

    public FeedbackObject(String name, String id, String email, String phone, String message) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
