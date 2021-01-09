package com.ys.model.pojo;

import java.util.Date;

public class Maintain {
    private Integer mId;

    private Date mTime;

    private String mContent;

    private Integer eId;

    private Integer mPeopleId;

    private String mPeopleName;

    private Integer pageNum;

    private Integer pageSize;

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