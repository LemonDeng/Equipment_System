package com.ys.model.pojo;

import java.util.Date;

public class Change {
    private Integer cId;

    private String ocName;

    private String ocType;

    private String ocFactory;

    private String ocCode;

    private String cContent;

    private Date cTime;

    private String cLocation;

    private Integer ccId;

    private Integer cPeopleId;

    private String newCname;

    private String newCtype;

    private String newCfactory;

    private String newCcode;

    private String changePeople;

    private Integer fId;


    public String getChangePeople() {
        return changePeople;
    }

    public void setChangePeople(String changePeople) {
        this.changePeople = changePeople;
    }

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public String getNewCname() {
        return newCname;
    }

    public void setNewCname(String newCname) {
        this.newCname = newCname;
    }

    public String getNewCtype() {
        return newCtype;
    }

    public void setNewCtype(String newCtype) {
        this.newCtype = newCtype;
    }

    public String getNewCfactory() {
        return newCfactory;
    }

    public void setNewCfactory(String newCfactory) {
        this.newCfactory = newCfactory;
    }

    public String getNewCcode() {
        return newCcode;
    }

    public void setNewCcode(String newCcode) {
        this.newCcode = newCcode;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getOcName() {
        return ocName;
    }

    public void setOcName(String ocName) {
        this.ocName = ocName == null ? null : ocName.trim();
    }

    public String getOcType() {
        return ocType;
    }

    public void setOcType(String ocType) {
        this.ocType = ocType == null ? null : ocType.trim();
    }

    public String getOcFactory() {
        return ocFactory;
    }

    public void setOcFactory(String ocFactory) {
        this.ocFactory = ocFactory == null ? null : ocFactory.trim();
    }

    public String getOcCode() {
        return ocCode;
    }

    public void setOcCode(String ocCode) {
        this.ocCode = ocCode == null ? null : ocCode.trim();
    }

    public String getcContent() {
        return cContent;
    }

    public void setcContent(String cContent) {
        this.cContent = cContent == null ? null : cContent.trim();
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    public String getcLocation() {
        return cLocation;
    }

    public void setcLocation(String cLocation) {
        this.cLocation = cLocation == null ? null : cLocation.trim();
    }

    public Integer getCcId() {
        return ccId;
    }

    public void setCcId(Integer ccId) {
        this.ccId = ccId;
    }

    public Integer getcPeopleId() {
        return cPeopleId;
    }

    public void setcPeopleId(Integer cPeopleId) {
        this.cPeopleId = cPeopleId;
    }
}