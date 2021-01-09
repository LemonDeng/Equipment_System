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

    /*设备维修内容 equipmentRepairContent*/
    void equipmentRepairContent(Integer eId, String content);
    /*得到维修id，供上传图片用*/
    int getRepairId(Integer eId);
    /*维修图片repairPictures*/
    void repairPictures(String p_name, Integer p_r_id);
}
