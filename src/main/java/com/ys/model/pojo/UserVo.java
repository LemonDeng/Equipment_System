package com.ys.model.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

@ApiModel(value = "UserVo对象",description = "用户")
public class UserVo {

    @ApiModelProperty(value = "工号")
    private String uWorknumber;

    @ApiModelProperty(value = "密码")
    private String uPassword;

    private Integer pageNum;

    private Integer pageSize;

    private String uName;

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getuWorknumber() {
        return uWorknumber;
    }

    public void setuWorknumber(String uWorknumber) {
        this.uWorknumber = uWorknumber;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }
}
