package com.ys.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.ys.common.ApiRestResponse;
import com.ys.model.pojo.Maintain;
import com.ys.model.request.RepairSearchReq;
import com.ys.model.request.UpdateMaintainReq;
import com.ys.model.vo.MaintainDetailVo;
import com.ys.model.vo.SearchVo;
import com.ys.service.MaintainService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/maintain")
public class MaintainController {

    @Autowired
    MaintainService maintainService;

    @ApiOperation("维护更新")
    @PostMapping("/update")
    @ResponseBody
    public ApiRestResponse maintainUpdate(@Valid @RequestBody UpdateMaintainReq updateMaintainReq)
    {
        Maintain maintain = new Maintain();
        BeanUtils.copyProperties(updateMaintainReq,maintain);
        maintainService.updateMaintain(maintain);
        return ApiRestResponse.success();
    }

//    *********************************************

    /*维护查询列表*/
    @GetMapping("/searchList")
    @ApiOperation("维护查询下拉列表")
    public ApiRestResponse maintainedSearchList(){
        List<SearchVo> maintainSearchList = maintainService.maintainSearchList();
        //将机台、设备等对应放进一个list集合
        List<String> eWorkshops = new ArrayList<>();
        List<String> eMachines =  new ArrayList<>();
        List<String> eNames =  new ArrayList<>();
        List<String> cNames =  new ArrayList<>();
        for(SearchVo searchVo : maintainSearchList){
            eWorkshops.add(searchVo.geteWorkshop());
            eMachines.add(searchVo.geteMachine());
            eNames.add(searchVo.geteName());
            cNames.add(searchVo.getcName());
        }

        JSONObject object1 = new JSONObject();
        object1.put("eWorkshops",eWorkshops);
        object1.put("eMachines",eMachines);
        object1.put("eNames",eNames);
        object1.put("cNames",cNames);

        return new ApiRestResponse().success(object1);
    }

    @PostMapping("/allMaintainList")
    @ApiOperation("维护分页查询功能：如果查询字段全部为空，就返回全部的列表，否则返回符合条件的列表")
    public ApiRestResponse search(@Valid @RequestBody RepairSearchReq repairSearchReq){

        PageInfo maintainList = maintainService.search(repairSearchReq);
        return new ApiRestResponse().success(maintainList);
    }

    @ApiOperation("删除维护数据")
    @PostMapping("/deleteMaintain")
    public ApiRestResponse deleteMaintainById(@Valid @RequestBody Maintain maintain){
        if(maintain.getmId()==null){
            return new ApiRestResponse().error(400,"id为空");
        }else{
            maintainService.deleteMaintainById(maintain.getmId());
            return new ApiRestResponse().success();
        }

    }




    @ApiOperation("设备维护内容和图片上传")
    @PostMapping("/equipmentMaintainContentAndImages")
    public ApiRestResponse equipmentMaintainContent(@RequestParam("e_id")Integer eId, @RequestParam("m_content") String content, @RequestParam("p_name") String iamgeName){
        maintainService.equipmentMaintainContent(eId,content);
        Integer p_m_id = maintainService.getMaintainId(eId);
        maintainService.maintainPictures(iamgeName,p_m_id);
        return new ApiRestResponse().success();
    }
}
