package com.ys.service;

import com.github.pagehelper.PageInfo;
import com.ys.model.pojo.Equipment;
import com.ys.model.pojo.Maintain;
import com.ys.model.request.ComponentChangeReq;
import com.ys.model.request.RepairSearchReq;
import com.ys.model.vo.EquipmentVo;
import com.ys.model.vo.SearchVo;

import java.util.List;

/**
 * 描述：设备Service
 */
public interface EquipmentService {



    void addEquipment(EquipmentVo equipmentVo);/*增加设备*/

    void updateEquipment(Equipment equipment);/*更新设备*/

    List<SearchVo>  equipmentSearchList(); /*searchList查询下拉列表*/

    PageInfo allEquipmentList(RepairSearchReq repairSearchReq);/*分页查询*/

    /*delete*/
    void delete(EquipmentVo equipmentVo);

    /*设备维修列表显示equipmentRepairList  车间、机台、设备名称、设备编码、零件名称、零件编码*/
   // ComponentVo equipmentRepairList();
    /*根据二维码的设备编号得到设备和零件名称和零件型号*/
   // PageInfo selectByECode(Integer pageNum, Integer pageSize, String code);

    /*基本数据分页列表和 根据零件名筛选*/
    PageInfo allBaseList(RepairSearchReq repairSearchReq);
    /*首页维护功能*/
    int maintain(Maintain maintain);
    /*首页维修功能*/
    void repair(ComponentChangeReq changeReq);
    /*首页更换功能 更换前先把旧零件的信息保存在ys_change中*/
    int saveOldComponent(ComponentChangeReq changeReq);
    /*首页更换功能  更换新的零件信息到ys_component*/
    int updateComponent(ComponentChangeReq changeReq);
}
