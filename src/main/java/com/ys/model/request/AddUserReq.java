package com.ys.model.request;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AddUserReq {



    private String uName;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Za-z])[\\x20-\\x7e]{10,10}$", message = "工号由10位数字和字母组成")
    private String uWorknumber;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Za-z])[\\x20-\\x7e]{8,16}$",message = "密码长度为8-16位,且由字母和数字组成")
    private String uPassword;


    @Pattern(regexp = "^((13[0-9])|(14[5,6,7,9])|(15[^4])|(16[5,6])|(17[0-9])|(18[0-9])|(19[1,8,9]))\\d{8}$",message = "手机号码格式错误")
    private String uPhone;


    @Range(min = 1,max = 3,message = "isadmin输入的数字无效")
    private Integer isadmin;


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
