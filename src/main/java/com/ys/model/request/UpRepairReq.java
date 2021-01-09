package com.ys.model.request;

import java.util.Date;

public class UpRepairReq {
    private Integer rId;

    private Date rTime;

    private String rContent;

    private Integer eId;

    private String rPeopleName;

    private Integer rPeopleId;

    public String getrPeopleName() {
        return rPeopleName;
    }

    public void setrPeopleName(String rPeopleName) {
        this.rPeopleName = rPeopleName;
    }

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public Date getrTime() {
        return rTime;
    }

    public void setrTime(Date rTime) {
        this.rTime = rTime;
    }

    public String getrContent() {
        return rContent;
    }

    public void setrContent(String rContent) {
        this.rContent = rContent == null ? null : rContent.trim();
    }

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public Integer getrPeopleId() {
        return rPeopleId;
    }

    public void setrPeopleId(Integer rPeopleId) {
        this.rPeopleId = rPeopleId;
    }
}