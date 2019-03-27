package com.example.shoppingapp.onlineshopping.Pojo;

import java.io.Serializable;

/**
 * Created by shoppingapp on 10/5/16.
 */

public class Products implements Serializable{
    String id,name,category,description,image,add_by,add_number,price,condition,add_date,end_date,status;
    //String ma liney diney kaam sab huncha paxi chayo bhanney casting garney
    //constructor and getter setter by alt insert gareko


    public Products(String id, String name, String category, String description, String image, String add_by, String add_number, String price, String condition, String add_date, String end_date, String status) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.image = image;
        this.add_by = add_by;
        this.add_number = add_number;
        this.price = price;
        this.condition = condition;
        this.add_date = add_date;
        this.end_date = end_date;
        this.status = status;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAdd_by() {
        return add_by;
    }

    public void setAdd_by(String add_by) {
        this.add_by = add_by;
    }

    public String getAdd_number() {
        return add_number;
    }

    public void setAdd_number(String add_number) {
        this.add_number = add_number;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getAdd_date() {
        return add_date;
    }

    public void setAdd_date(String add_date) {
        this.add_date = add_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
