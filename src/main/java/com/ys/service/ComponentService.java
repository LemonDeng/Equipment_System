package com.ys.service;

import com.github.pagehelper.PageInfo;
import com.ys.model.pojo.Component;
import com.ys.model.vo.ComponentVo;
import com.ys.model.request.RepairSearchReq;
import com.ys.model.vo.SearchVo;

import java.util.List;

/**
 * 描述：ComponentService
 */

public interface ComponentService {


    void addComponent(Component component);

    void updateComponent(Component component);


   // PageInfo allComponentList(ComponentVo componentVo);

    PageInfo allComponentList(RepairSearchReq repairSearchReq);

    /****************/
    /*查询下拉列表*/
    List<SearchVo> componentSearchList();

    /*<!--delete-->*/
    void delete(ComponentVo componentVo);

    /*根据零件cId，得到厂家fId */
    int getFidByCid(int cId);
    /*得到所有的零件名*/
    /*得到所有的零件名*/
    List<SearchVo> allCName();
}
