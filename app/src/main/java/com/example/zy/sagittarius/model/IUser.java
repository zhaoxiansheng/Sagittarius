package com.example.zy.sagittarius.model;

/**
 * Created on 2017/6/11.
 *
 * @author zhaoy
 */

public interface IUser {

    /**
     * 获取账号
     *
     * @return 账号
     */
    String getName();

    /**
     * 获取密码
     *
     * @return 密码
     */
    String getPasswd();

    /**
     * 登陆检查
     *
     * @param name   账号
     * @param passwd 密码
     * @return 返回是否登陆成功
     */
    int checkUserValidity(String name, String passwd);

}
