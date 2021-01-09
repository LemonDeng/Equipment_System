package com.ys.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ys.model.pojo.*;

import java.util.Date;

public class ChangeDetailVo {
    /*equipment的信息*/
    private Integer eId;

    private String eWorkshop;

    private String eMachine;

    private String eName;

    private String eCode;

    private String eType;

    private String eFname;

    /*component的信息*/
    private Integer cId;

    private String cName;

    private String cCode;

    private String cType;

    private String cFname;

    /*change的信息*/
    private Integer changeId;

    private String ocName;

    private String ocType;

    private String ocFactory;

    private String ocCode;

    private String changePeopleName;

    private String changeContent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date changeTime;



    /*picture的信息*/
    private Integer pId;
    private String pName;


    /*user的信息*/
    private Integer uId;
    private String uName;

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
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

    public String geteCode() {
        return eCode;
    }

    public void seteCode(String eCode) {
        this.eCode = eCode;
    }

    public String geteType() {
        return eType;
    }

    public void seteType(String eType) {
        this.eType = eType;
    }

    public String geteFname() {
        return eFname;
    }

    public void seteFname(String eFname) {
        this.eFname = eFname;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcCode() {
        return cCode;
    }

    public void setcCode(String cCode) {
        this.cCode = cCode;
    }

    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType;
    }

    public String getcFname() {
        return cFname;
    }

    public void setcFname(String cFname) {
        this.cFname = cFname;
    }

    public Integer getChangeId() {
        return changeId;
    }

    public void setChangeId(Integer changeId) {
        this.changeId = changeId;
    }

    public String getOcName() {
        return ocName;
    }

    public void setOcName(String ocName) {
        this.ocName = ocName;
    }

    public String getOcType() {
        return ocType;
    }

    public void setOcType(String ocType) {
        this.ocType = ocType;
    }

    public String getOcFactory() {
        return ocFactory;
    }

    public void setOcFactory(String ocFactory) {
        this.ocFactory = ocFactory;
    }

    public String getOcCode() {
        return ocCode;
    }

    public void setOcCode(String ocCode) {
        this.ocCode = ocCode;
    }

    public String getChangePeopleName() {
        return changePeopleName;
    }

    public void setChangePeopleName(String changePeopleName) {
        this.changePeopleName = changePeopleName;
    }

    public String getChangeContent() {
        return changeContent;
    }

    public void setChangeContent(String changeContent) {
        this.changeContent = changeContent;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

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
        this.uName = uName;
    }
}
