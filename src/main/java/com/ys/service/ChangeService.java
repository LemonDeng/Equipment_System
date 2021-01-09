package com.ys.service;

import com.github.pagehelper.PageInfo;
import com.ys.model.pojo.Change;
import com.ys.model.pojo.ChangeVo;
import com.ys.model.request.RepairSearchReq;
import com.ys.model.vo.ChangeDetailVo;
import com.ys.model.vo.SearchVo;

import java.util.List;

public interface ChangeService {
    /*更换更新*/
    void updateChange(ChangeVo changevo);


    /******************/
    /*更换查询列表*/
    List<SearchVo> changeSearchList();
    /*根据更换编号删除更换数据*/
    void deleteById(Integer id);
    /*查询的实现*/
    PageInfo search(RepairSearchReq repairSearchReq);

}
