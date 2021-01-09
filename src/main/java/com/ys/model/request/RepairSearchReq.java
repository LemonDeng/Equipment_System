package com.ys.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class RepairSearchReq {
    private Integer eId;
    private String eWorkshop;
    private String eMachine;
    private String eName;
    private String cName;
    private String uName;
    private Integer pageNum;
    private Integer  pageSize;

    /*注解实现：将前端传回的字符串自动转成指定的Date类型*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String endTime;

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public String geteWorkshop() {
        return eWorkshop;
    }

    public void seteWorkshop(String eWorkshop) {
        this.eWorkshop = eWorkshop;
    }

    public String geteMachine() {
        return eMachine;
    }

    public void seteMachine(String eMachine) {
        this.eMachine = eMachine;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    @Override
    public String toString() {
        return "RepairSearchReq{" +
                "eWorkshop='" + eWorkshop + '\'' +
                ", eMachine='" + eMachine + '\'' +
                ", eName='" + eName + '\'' +
                ", cName='" + cName + '\'' +
                ", uName='" + uName + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
