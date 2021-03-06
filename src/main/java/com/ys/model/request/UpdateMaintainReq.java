package com.ys.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UpdateMaintainReq {
    private Integer mId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date mTime;

    private String mContent;

    private Integer eId;

    private Integer mPeopleId;

    private String mPeopleName;

    public String getmPeopleName() {
        return mPeopleName;
    }

    public void setmPeopleName(String mPeopleName) {
        this.mPeopleName = mPeopleName;
    }

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    public Date getmTime() {
        return mTime;
    }

    public void setmTime(Date mTime) {
        this.mTime = mTime;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent == null ? null : mContent.trim();
    }

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public Integer getmPeopleId() {
        return mPeopleId;
    }

    public void setmPeopleId(Integer mPeopleId) {
        this.mPeopleId = mPeopleId;
    }
}