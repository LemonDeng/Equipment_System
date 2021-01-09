package com.ys.model.pojo;

public class Picture {
    private Integer pId;

    private String pName;

    private Integer pRId;

    private Integer pMId;

    private Integer pCId;

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
        this.pName = pName == null ? null : pName.trim();
    }

    public Integer getpRId() {
        return pRId;
    }

    public void setpRId(Integer pRId) {
        this.pRId = pRId;
    }

    public Integer getpMId() {
        return pMId;
    }

    public void setpMId(Integer pMId) {
        this.pMId = pMId;
    }

    public Integer getpCId() {
        return pCId;
    }

    public void setpCId(Integer pCId) {
        this.pCId = pCId;
    }
}