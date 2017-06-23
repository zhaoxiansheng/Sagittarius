package com.example.zy.sagittarius.model;

/**
 * Created by ZY on 2017/6/11.
 */

public interface IUser {

    String getName();

    String getPasswd();

    int checkUserValidity(String name, String passwd);

}
