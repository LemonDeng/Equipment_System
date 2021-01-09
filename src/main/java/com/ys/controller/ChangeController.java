package com.ys.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.ys.common.ApiRestResponse;
import com.ys.model.pojo.Change;
import com.ys.model.pojo.ChangeVo;
import com.ys.model.request.RepairSearchReq;
import com.ys.model.request.UpChangeReq;
import com.ys.model.vo.ChangeDetailVo;
import com.ys.model.vo.SearchVo;
import com.ys.service.ChangeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/change")
public class ChangeController {
    @Autowired
    ChangeService changeService;

    /**
     * 更换更新
     * @param changeVo
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public ApiRestResponse updateChange(@Valid @RequestBody ChangeVo changeVo)
    {
        changeService.updateChange(changeVo);
        return ApiRestResponse.success();
    }
    /****************************************/

    /*维修查询列表*/
    @GetMapping("/searchList")
    @ApiOperation("更换查询下拉列表")
    @ResponseBody
    public ApiRestResponse repairedSearchList(){
        List<SearchVo> changeSearchList = changeService.changeSearchList();

        //将机台、设备等对应放进一个list集合
        List<String> eWorkshops = new ArrayList<>();
        List<String> eMachines =  new ArrayList<>();
        List<String> eNames =  new ArrayList<>();
        List<String> cNames =  new ArrayList<>();
        for(SearchVo searchVo : changeSearchList){
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

    /*删除记录*/
    @ApiOperation("删除更换数据")
    @PostMapping("/deleteChange")
    public ApiRestResponse deleteRepairById(@Valid @RequestBody Change change){
        if(change.getcId() == null){
            return new ApiRestResponse().error(400,"id不能为空！");
        }else{
            changeService.deleteById(change.getcId());
            return new ApiRestResponse().success();
        }


    }

    @PostMapping("/allChangeList")
    @ApiOperation("更新分页查询功能：如果查询字段全部为空，就返回全部的列表，否则返回符合条件的列表")
    public ApiRestResponse search(@Valid @RequestBody RepairSearchReq repairSearchReq){
        PageInfo changeList = changeService.search(repairSearchReq);
        return new ApiRestResponse().success(changeList);
    }

}
