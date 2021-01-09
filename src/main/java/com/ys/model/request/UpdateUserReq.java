package com.ys.model.request;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UpdateUserReq {

    @NotNull(message = "id不能为null")
    private Integer uId;
    @Size(min = 2, max = 5)
    private String uName;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Za-z])[\\x20-\\x7e]{10,10}$", message = "工号由10位数字和字母组成")
    @Size(max = 10,min = 10)
    private String uWorknumber;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Za-z])[\\x20-\\x7e]{8,16}$",message = "密码长度为8-16位,且由字母和数字组成")
    @Length(min = 8, max = 18, message = "密码长度为8-18位。")
    private String uPassword;


    @Pattern(regexp = "^((13[0-9])|(14[5,6,7,9])|(15[^4])|(16[5,6])|(17[0-9])|(18[0-9])|(19[1,8,9]))\\d{8}$",message = "手机号码格式错误")
    private String uPhone;


    @Range(min = 1,max = 2,message = "isadmin只能是1,2")
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
