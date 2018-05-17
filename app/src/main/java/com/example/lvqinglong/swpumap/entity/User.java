package com.example.lvqinglong.swpumap.entity;

import java.io.Serializable;

/**
 * Created by chebole on 2018/5/2.
 */

public class User implements Serializable {

    Integer        id;          //id
    String         userName;    //昵称
    String         password;    //密码
    String         email;       //邮箱

    Double         latitude;    //纬度
    Double         longitude;   //经度

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
