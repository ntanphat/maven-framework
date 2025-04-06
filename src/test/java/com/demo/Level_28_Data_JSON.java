package com.demo;

import jsonData.nopCommerce.UserInfo;
import org.testng.annotations.Test;

public class Level_28_Data_JSON {
    @Test
    public void TC01 (){
        System.out.println("HelloWorld");
        UserInfo userInfo = UserInfo.getUserInfo("userData.json");
        System.out.println(userInfo.getFirstName());
        System.out.println(userInfo.getLastName());
        System.out.println(userInfo.getEmail());
        System.out.println(userInfo.getPassword());
        System.out.println(userInfo.getNumber());
    }
}
