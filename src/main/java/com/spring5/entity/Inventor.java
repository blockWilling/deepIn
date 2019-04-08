package com.spring5.entity;

import java.util.Date;

/**
 * Created by blockWilling on 2019/2/13.
 */
public class Inventor {
    private  String serbian;
    private  Date time;
    private  String name;

    public Inventor() {
    }

    public Inventor(String s, Date time, String serbian) {
    this.name=s;
    this.time=time;
    this.serbian=serbian;
    }

    public String getSerbian() {
        return serbian;
    }

    public void setSerbian(String serbian) {
        this.serbian = serbian;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
