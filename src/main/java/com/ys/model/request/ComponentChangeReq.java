package com.ys.model.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.models.auth.In;

import java.util.Date;

public class ComponentChangeReq {
    private Integer flag;
    /*新零件信息*/
    private Integer eId; //所属设备
    private Integer fId;//新厂家id
    private Integer cId;
    private String newCname;
    private String newCcode;
    private String newCtype;
    private String newFactory;
    private Integer lifespan;
    /*旧零件信息*/
    private String oldCname;
    private String oldCcode;
    private String oldCtype;
    private String oldFactory;

    private String content;//更换或维修的内容


    private Date time;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getNewCname() {
        return newCname;
    }

    public void setNewCname(String newCname) {
        this.newCname = newCname;
    }

    public String getNewCcode() {
        return newCcode;
    }

    public void setNewCcode(String newCcode) {
        this.newCcode = newCcode;
    }

    public String getNewCtype() {
        return newCtype;
    }

    public void setNewCtype(String newCtype) {
        this.newCtype = newCtype;
    }

    public String getNewFactory() {
        return newFactory;
    }

    public void setNewFactory(String newFactory) {
        this.newFactory = newFactory;
    }

    public Integer getLifespan() {
        return lifespan;
    }

    public void setLifespan(Integer lifespan) {
        this.lifespan = lifespan;
    }

    public String getOldCname() {
        return oldCname;
    }

    public void setOldCname(String oldCname) {
        this.oldCname = oldCname;
    }

    public String getOldCcode() {
        return oldCcode;
    }

    public void setOldCcode(String oldCcode) {
        this.oldCcode = oldCcode;
    }

    public String getOldCtype() {
        return oldCtype;
    }

    public void setOldCtype(String oldCtype) {
        this.oldCtype = oldCtype;
    }

    public String getOldFactory() {
        return oldFactory;
    }

    public void setOldFactory(String oldFactory) {
        this.oldFactory = oldFactory;
    }

    }
