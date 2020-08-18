package com.lc.mmallbook.common;

/**
 * @author lhm
 * @date 2020/7/11 0:12
 */
public class Const {

    public  static final String CURRENT_USER = "currentUser";

    public  static final String USERNAME = "username";

    public  static final String EMAIL = "email";

    public interface Role{
        int ROLE_CUSTOMER = 0; //普通用户
        int ROLE_ADMIN = 1;//管理员
    }
}
