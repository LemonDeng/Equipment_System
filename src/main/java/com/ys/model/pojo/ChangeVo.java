package com.ys.model.pojo;

import java.util.Date;

public class ChangeVo {
    private Change change;
    private Component component;
    private User user;
    private Equipment equipment;

    private String newCname;

    private String newCtype;

    private String newCfactory;

    private String newCcode;

    private String changePeople;

    private String cLocation;

    private Date startTime;

    private int cid;

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

    public String getChangePeople() {
        return changePeople;
    }

    public void setChangePeople(String changePeople) {
        this.changePeople = changePeople;
    }

    public String getcLocation() {
        return cLocation;
    }

    public void setcLocation(String cLocation) {
        this.cLocation = cLocation;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public Change getChange() {
        return change;
    }

    public void setChange(Change change) {
        this.change = change;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }
}
