package com.ys.service;

import com.github.pagehelper.PageInfo;
import com.ys.model.pojo.Maintain;
import com.ys.model.request.RepairSearchReq;
import com.ys.model.vo.MaintainDetailVo;
import com.ys.model.vo.SearchVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 维护Service
 */
public interface MaintainService {
    void updateMaintain(Maintain maintain);
    /***************/

    /*根据维护id删除维修信息*/
    void deleteMaintainById(Integer id);

    /*维护查询列表*/
    List<SearchVo> maintainSearchList();

    /*查询的实现*/
    //PageInfo search(Integer pageNum,Integer pageSize,String e_workshop,String e_machine,String e_name,String c_name,String u_name);
    PageInfo search(RepairSearchReq repairSearchReq);

    /*根据设备维护内容获得维护id，供上传维护图片用getMaintainId*/
    int getMaintainId(Maintain maintain);
    /*维护图片maintainPictures*/
    void maintainPictures(String pName, Integer pMId);
}
