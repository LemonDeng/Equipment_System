package com.ys.service;

import com.github.pagehelper.PageInfo;
import com.ys.model.pojo.Repair;
import com.ys.model.request.RepairSearchReq;
import com.ys.model.vo.RepairDetailVo;
import com.ys.model.vo.SearchVo;

import java.util.List;


public interface RepairService {
    void updateRepair(Repair repair);

    /***********************/

    /*查询的实现*/
    PageInfo search(RepairSearchReq repairSearchReq);

    /*根据维修id删除维修信息*/
    void deleteRepairById(Integer id);

    /*维修查询列表*/
    List<SearchVo> repairSearchList();


    /*根据设备维修内容获得维护id，供上传维护图片用*/
    int getRepairId(String rContent);
    /*维修图片*/
    void repairPictures(String pName, Integer pRId);
}
