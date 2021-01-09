package com.ys.model.pojo;

public class User {
    private Integer uId;

    private String uName;

    private String uWorknumber;

    private String uPassword;

    private String uPhone;

    private Integer isadmin;

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName == null ? null : uName.trim();
    }

    public String getuWorknumber() {
        return uWorknumber;
    }

    public void setuWorknumber(String uWorknumber) {
        this.uWorknumber = uWorknumber == null ? null : uWorknumber.trim();
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword == null ? null : uPassword.trim();
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone == null ? null : uPhone.trim();
    }

    public Integer getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(Integer isadmin) {
        this.isadmin = isadmin;
    }
}