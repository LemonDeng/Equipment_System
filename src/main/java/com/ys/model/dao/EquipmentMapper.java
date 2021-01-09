package com.ys.model.dao;

import com.ys.model.pojo.Maintain;
import com.ys.model.request.ComponentChangeReq;
import com.ys.model.vo.ComponentVo;
import com.ys.model.pojo.Equipment;
import com.ys.model.vo.EquipmentVo;
import com.ys.model.request.RepairSearchReq;
import com.ys.model.vo.SearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentMapper {
    int deleteByPrimaryKey(Integer eId);

    int insert(Equipment record);

    int insertSelective(Equipment record);

    Equipment selectByPrimaryKey(Integer eId);

    int updateByPrimaryKeySelective(Equipment record);

    //int updateByEcode(Equipment record);

    int updateByPrimaryKey(Equipment record);

    Equipment selectByCode(String code);

    void insertEquipment(@Param("eWorkshop") String eWorkshop, @Param("eMachine") String eMachine, @Param("eName") String eName,
                         @Param("eCode") String eCode,
                         @Param("eType") String eType,
                         @Param("fId") Integer fId);

    int selectByFCode(String e_code);

    int selectCode(String e_code);


    /************************************/
    /*searchList查询下拉列表*/
    List<SearchVo>  equipmentSearchList();
    /*allList* 分页查询列表和查询的实现*/
    List<Equipment> allEquipmentList(RepairSearchReq repairSearchReq);
    /*delete*/
    void delete(Integer id);
    /*基本数据分页列表 和 根据零件名筛选*/
    List<ComponentVo> allBaseList(RepairSearchReq repairSearchReq);
    /*根据二维码的设备编号得到设备和零件名称和零件型号*/
    //List<ComponentVo>selectByECode(@Param("e_code") String code);
    /*首页维护功能*/
    int maintain(Maintain maintain);
    /*首页维修功能*/
    void repair(ComponentChangeReq changeReq);
   /*首页更换功能 更换前先把旧零件的信息保存在ys_change中*/
    int saveOldComponent(ComponentChangeReq changeReq);
    /*首页更换功能  更换新的零件信息到ys_component*/
    int updateComponent(ComponentChangeReq changeReq);



}