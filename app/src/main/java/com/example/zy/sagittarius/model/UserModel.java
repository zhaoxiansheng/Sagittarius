package com.example.zy.sagittarius.model;

/**
 * Created by ZY on 2017/6/11.
 */

public class UserModel implements IUser {
    String name;
    String passwd;

    public UserModel(String name, String passwd) {
        this.name = name;
        this.passwd = passwd;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPasswd() {
        return passwd;
    }

    @Override
    public int checkUserValidity(String name, String passwd){
        if (name==null && passwd==null){
            return -1;
        } else if (passwd.equals(getPasswd()) && name.equals(getName())){
            return 0;
        } else {
            return -2;
        }
    }
}
