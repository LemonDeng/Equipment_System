package com.ys.model.dao;

import com.ys.model.pojo.Factory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FactoryMapper {
    int deleteByPrimaryKey(Integer fId);

    int insert(Factory record);

    int insertSelective(Factory record);

    Factory selectByPrimaryKey(Integer fId);

    int updateByPrimaryKeySelective(Factory record);

    int updateByPrimaryKey(Factory record);

    void insertE_FName(@Param("fName") String fName);

    void insertC_FName(@Param("fName") String fName);

    int selectFid(@Param("f_name") String f_name, @Param("f_type") String f_type);
    //int selectFid(@Param("f_name") String f_name);

    Factory selectByFactoryName(@Param("fName") String fName);


    /*自定义--          lhy*/
    /*新增零件厂家*/
    int insertCFName(String fName);
    /*根据厂家姓名和typ得到零件厂家id*/
    int getFid(String fName,Integer fType);
    /*根据厂家fId 和 type 得到厂家名称*/
    String getFname(int fId,int fType);
}