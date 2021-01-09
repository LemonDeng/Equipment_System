package com.ys.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.ys.common.ApiRestResponse;
import com.ys.exception.YsLjExceptionEnum;
import com.ys.model.pojo.Component;
import com.ys.model.vo.ComponentVo;
import com.ys.model.request.RepairSearchReq;
import com.ys.model.request.UpComponentReq;
import com.ys.model.vo.SearchVo;
import com.ys.service.ComponentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 *ComponentController
 */
@RestController
@RequestMapping("/component")
public class ComponentController {
    @Autowired
    ComponentService componentService;

    /**
     * 新增零件信息
     *
     */
    @ApiOperation("新增零件信息")
    @PostMapping("/addComponent")
    @ResponseBody
    public ApiRestResponse addComponent(@Valid @RequestBody Component component)

    {
        if (component.getcCode().length() != 11) {
            return ApiRestResponse.error(YsLjExceptionEnum.ECODE_EXISTED);
        }
        for (int i = component.getcCode().length() ; --i >= 0; ) {
            if (!Character.isDigit(component.getcCode().charAt(i))) {
                return ApiRestResponse.error(YsLjExceptionEnum.ECODE_EXISTED);
            }
        }
        componentService.addComponent(component);
        return ApiRestResponse.success();
    }

    /**
     * 修改零件信息
     * @param upComponentReq
     * @return
     */
    @ApiOperation("修改零件信息")
    @PostMapping("/update")
    @ResponseBody
    public ApiRestResponse updateComponent(@Valid @RequestBody UpComponentReq upComponentReq) {

        if (upComponentReq.getcCode().length() != 11) {
            return ApiRestResponse.error(YsLjExceptionEnum.ECODE_EXISTED);
        }
        for (int i = upComponentReq.getcCode().length(); --i >= 0; ) {
            if (!Character.isDigit(upComponentReq.getcCode().charAt(i))) {
                return ApiRestResponse.error(YsLjExceptionEnum.ECODE_EXISTED);
            }
        }
        Component component = new Component();
        BeanUtils.copyProperties(upComponentReq,component);
        componentService.updateComponent(component);
        return ApiRestResponse.success();
    }

    /**
     * 零件列表分页查询
     * @param
     * @param
     * @return
     */
    @PostMapping("/allComponentList")
    @ApiOperation("零件列表")
    @ResponseBody
    public ApiRestResponse componentList(@Valid @RequestBody RepairSearchReq repairSearchReq){
        System.out.println();
        PageInfo componentList = componentService.allComponentList(repairSearchReq) ;
        return new ApiRestResponse().success(componentList);
    }

    /**
     * 删除零件数据
     * @param
     * @return
     */
    @ApiOperation("删除零件数据")
    @PostMapping("/deleteComponent")
    @ResponseBody
    public ApiRestResponse deleteCcomponentById(@RequestBody ComponentVo componentVo){
            componentService.delete(componentVo);
            return new ApiRestResponse().success();

    }

    @GetMapping("/searchList")
    @ApiOperation("设备零件查询下拉列表")
    public ApiRestResponse componentSearchList() {
        List<SearchVo> componentSearchList = componentService.componentSearchList();

        //将机台、设备等对应放进一个list集合
        List<String> eWorkshops = new ArrayList<>();
        List<String> eMachines =  new ArrayList<>();
        List<String> eNames =  new ArrayList<>();
        List<String> cNames =  new ArrayList<>();
        for(SearchVo searchVo : componentSearchList){
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

}
