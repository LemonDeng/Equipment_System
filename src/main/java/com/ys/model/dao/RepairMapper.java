package com.ys.model.dao;

import com.ys.model.pojo.Repair;
import com.ys.model.request.RepairSearchReq;
import com.ys.model.vo.RepairDetailVo;
import com.ys.model.vo.SearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairMapper {
    int updateByEidSelective(Repair record);
    /************************************/


    /*根据维修id删除维修信息*/
    void deleteRepairById(Integer id);

    /*维修查询列表*/
    List<SearchVo> repairSearchList();

    /*查询的实现*/
    List<RepairDetailVo> search(RepairSearchReq repairSearchReq);

    /*设备维修内容 equipmentRepairContent*/
    void equipmentRepairContent(@Param("e_id") Integer eId, @Param("r_content") String content);

    /*维修图片的上传 equipmentRepairImages*/
    void equipmentImages(@Param("e_id") Integer eId, @Param("r_image") String images);
    /*得到维修id，供上传图片用*/
    int getRepairId(@Param("e_id") Integer eId);
    /*维修图片repairPictures*/
    void repairPictures(@Param("p_name") String p_name, @Param("p_r_id") Integer p_r_id);

}