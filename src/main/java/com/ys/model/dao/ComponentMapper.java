package com.ys.model.dao;

import com.ys.model.pojo.Change;
import com.ys.model.pojo.Component;
import com.ys.model.vo.ComponentVo;
import com.ys.model.request.RepairSearchReq;
import com.ys.model.vo.SearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ComponentMapper {


    int insert(Component record);

    int insertSelective(Component record);

    Component selectByPrimaryKey(Integer cId);

    int updateByPrimaryKeySelective(Component record);

    int updateBySelective(Component changevo);

    int updateByPrimaryKey(Component record);


    Component selectByCode(String c_code);

    void insertComponent(@Param("c_name") String c_name,
                         @Param("c_code") String c_code,
                         @Param("c_type") String c_type,
                         @Param("c_location") String c_location,
                         @Param("lifespan") String lifespan,
                         @Param("starttime") Date starttime,
                         @Param("e_id") Integer e_id,
                         @Param("f_id") Integer f_id);

    void insertruntime(@Param("runtime") Date runtime);

    /*****************************************************/
    /*allList*/
    List<ComponentVo> allList(RepairSearchReq repairSearchReq);
    /*<!--delete-->*/
    void delete(String cCode);
    int deleteByPrimaryKey(Integer cId);


    /*查询下拉列表*/
    List<SearchVo>  componentSearchList();

    /*得到所有的零件名*/
    List<SearchVo> allCName();

    /*根据零件cId，得到厂家fId */
    int getFidByCid(int cId);

    List<Component> qrCode(@Param("eId") Integer eId);
}