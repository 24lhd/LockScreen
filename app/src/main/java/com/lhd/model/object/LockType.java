package com.lhd.model.object;

/**
 * Created by D on 7/13/2017.
 */

public class LockType {
    String name;
    String pass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public LockType(String name, String pass) {

        this.name = name;
        this.pass = pass;
    }
}
