package com.ys.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.ys.common.ApiRestResponse;
import com.ys.model.pojo.Repair;
import com.ys.model.request.RepairSearchReq;
import com.ys.model.request.UpRepairReq;
import com.ys.model.vo.RepairDetailVo;
import com.ys.model.vo.SearchVo;
import com.ys.service.RepairService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/repair")
public class RepairController {

    @Autowired
    RepairService repairService;

    /**
     * 维修更新
     * @param upRepairReq
     * @return
     */
    @ApiOperation(value = "维修更新",notes = "cId:维修的更新(String) rTime:维修时间(Date)rContent:维修内容(String)rPeopleName:维修人员")
    @PostMapping("/update")
    @ResponseBody
    public ApiRestResponse updateRepair(@Valid @RequestBody UpRepairReq upRepairReq)
    {
        Repair repair = new Repair();
        BeanUtils.copyProperties(upRepairReq,repair);
        repairService.updateRepair(repair);
        return ApiRestResponse.success();
    }



    /*维修查询列表*/
    @GetMapping("/searchList")
    @ApiOperation("维修查询下拉列表")
    public ApiRestResponse repairedSearchList() {
        List<SearchVo> repairSearchList = repairService.repairSearchList();
        //将机台、设备等对应放进一个list集合
        List<String> eWorkshops = new ArrayList<>();
        List<String> eMachines =  new ArrayList<>();
        List<String> eNames =  new ArrayList<>();
        List<String> cNames =  new ArrayList<>();
        for(SearchVo searchVo : repairSearchList){
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

    @PostMapping("/allRepairList")
    @ApiOperation("维修分页查询功能：如果查询字段全部为空，就返回全部的列表，否则返回符合条件的列表")
    public ApiRestResponse search(@Valid @RequestBody RepairSearchReq repairSearchReq) {
        PageInfo repairList = repairList = repairService.search(repairSearchReq);
        return new ApiRestResponse().success(repairList);
    }


    /*删除记录*/
    @ApiOperation("删除维修数据")
    @PostMapping("/deleteRepair")
    public ApiRestResponse deleteRepairById(@Valid @RequestBody Repair repair){
        if(repair.getrId()==null){
            return new ApiRestResponse().error(400,"id为空");
        }else{
            repairService.deleteRepairById(repair.getrId());
            return new ApiRestResponse().success();
        }
    }


}
