package com.ys.model.dao;

import com.ys.model.pojo.Maintain;
import com.ys.model.request.RepairSearchReq;
import com.ys.model.vo.MaintainDetailVo;
import com.ys.model.vo.SearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MaintainMapper {
    int deleteByPrimaryKey(Integer mId);

    int insert(Maintain record);

    int insertSelective(Maintain record);

    Maintain selectByPrimaryKey(Integer mId);

    int updateByPrimaryKeySelective(Maintain record);

    int updateByPrimaryKey(Maintain record);

    void insertMid(Integer m_people_id);

    int updateByEidSelective(Maintain record);
/************************************************/

    /*根据维护id删除维修信息*/
    void deleteMaintainById(Integer id);

    /*维护查询列表*/
    List<SearchVo> maintainSearchList();

    /*查询的实现*/
    // List<MaintainVo> search(@Param("eWorkshop")String e_workshop, @Param("eMachine")String e_machine, @Param("eName")String e_name,  @Param("cName")String c_name, @Param("uName")String u_name);
    List<MaintainDetailVo> search(RepairSearchReq repairSearchReq);

    /*根据设备维护内容获得维护id，供上传维护图片用getMaintainId*/
    int getMaintainId(Maintain maintain);
    /*维护图片maintainPictures*/
    void maintainPictures(String pName, Integer pMId);

}