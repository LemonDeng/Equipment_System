package com.ys.model.dao;

import com.ys.model.pojo.Change;
import com.ys.model.request.RepairSearchReq;
import com.ys.model.vo.ChangeDetailVo;
import com.ys.model.vo.SearchVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ChangeMapper {


    int insertSelective(Change record);

    Change selectByPrimaryKey(Integer cId);

    int updateByPrimaryKeySelective(Change record);

    int updateBySelective(Change record);

    int updateByPrimaryKey(Change record);

    void insertOcomponent(@Param("oc_name") String ocName,
                          @Param("oc_type") String ocType,
                          @Param("oc_factory") String ocFactory,
                          @Param("oc_code") String ocCode,
                          @Param("cContent") String cContent,
                          @Param("cTime") Date cTime,
                          @Param("cLocation") String cLocation,
                          @Param("ccId") Integer ccId,
                          @Param("cPeopleId") Integer cPeopleId);

    /*****************************************************/

    /*更换查询列表*/
    List<SearchVo> changeSearchList();

    /*根据更换编号删除更换数据*/
    void deleteById(Integer id);
    /*查询的实现*/
   List<ChangeDetailVo> search(RepairSearchReq repairSearchReq);

/*更换更新插入功能*/
    int insert(Change record);

}