package com.ys.service;

/**
 * 描述：UserService
 */

public interface FactoryService {

   /* void insertFName(String f_name,String f_address,String f_type);

    int selectFid(String f_name);*/

    /*自定义--lhy*/
    /*新增零件厂家*/
    int insertCFName(String fName);
    /*根据厂家姓名和typ得到零件厂家id*/
    int getFid(String fName,Integer fType);
    /*根据厂家fId 和 type 得到厂家名称*/
    String getFname(int fId,int fType);

}
