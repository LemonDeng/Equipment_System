package com.ys.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.ys.common.ApiRestResponse;
import com.ys.exception.YsLjExceptionEnum;
import com.ys.model.pojo.Equipment;
import com.ys.model.request.RepairSearchReq;
import com.ys.model.vo.EquipmentVo;
import com.ys.model.request.UpEquipmentReq;
import com.ys.model.vo.SearchVo;
import com.ys.service.EquipmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * EquipmentController
 */
@RestController
@RequestMapping("/equipment")
public class EquipmentController {
    @Autowired
    EquipmentService equipmentService;

    /**
     * 增加设备
     *
     */
    @ApiOperation("添加设备")
    @PostMapping("/addEquipment")
    @ResponseBody
    public ApiRestResponse addEquipment(@RequestBody EquipmentVo equipmentVo) {
        if (equipmentVo.geteCode().length() != 11) {
            return ApiRestResponse.error(YsLjExceptionEnum.ECODE_EXISTED);
        }
        for (int i = equipmentVo.geteCode().length(); --i >= 0; ) {
            if (!Character.isDigit(equipmentVo.geteCode().charAt(i))) {
                return ApiRestResponse.error(YsLjExceptionEnum.ECODE_EXISTED);
            }
        }

        equipmentService.addEquipment(equipmentVo);
        return ApiRestResponse.success();
    }

    /**
     * 修改设备信息
     *
     * @param upEquipmentReq
     * @return
     */
    @ApiOperation(value = "修改设备信息",produces = "application/json")
    //@PostMapping(value = "/updateEquipment")
    @RequestMapping(value="/updateEquipment",method = RequestMethod.POST)
    @ResponseBody
    public ApiRestResponse updateEquipment( @RequestBody UpEquipmentReq upEquipmentReq) {

        if (upEquipmentReq.geteCode().length() != 11) {
            return ApiRestResponse.error(YsLjExceptionEnum.ECODE_EXISTED);
        }
        for (int i = upEquipmentReq.geteCode().length(); --i >= 0; ) {
            if (!Character.isDigit(upEquipmentReq.geteCode().charAt(i))) {
                return ApiRestResponse.error(YsLjExceptionEnum.ECODE_EXISTED);
            }
        }
        Equipment equipment = new Equipment();
        BeanUtils.copyProperties(upEquipmentReq, equipment);
        equipmentService.updateEquipment(equipment);
        return ApiRestResponse.success();
    }

    /**
     * 设备查询列表
     *
     * @return
     */
    @GetMapping("/searchList")
    @ApiOperation("设备查询下拉列表")
    public ApiRestResponse equipmentSearchList() {
        List<SearchVo> equipmentSearchList = equipmentService.equipmentSearchList();

        //将机台、设备等对应放进一个list集合
        List<String> eWorkshops = new ArrayList<>();
        List<String> eMachines =  new ArrayList<>();
        List<String> eNames =  new ArrayList<>();
        for(SearchVo searchVo : equipmentSearchList){
            eWorkshops.add(searchVo.geteWorkshop());
            eMachines.add(searchVo.geteMachine());
            eNames.add(searchVo.geteName());
        }

        JSONObject object1 = new JSONObject();
        object1.put("eWorkshops",eWorkshops);
        object1.put("eMachines",eMachines);
        object1.put("eNames",eNames);

        return new ApiRestResponse().success(object1);
    }

    /**
     *
     * @param repairSearchReq
     * @return
     */
    @ApiOperation("设备分页查询")
   @PostMapping("/allEquipmentList")
   @ResponseBody
   public ApiRestResponse allEquipmentList(@Valid @RequestBody RepairSearchReq repairSearchReq)
   {
       PageInfo pageInfo = equipmentService.allEquipmentList(repairSearchReq);
       return ApiRestResponse.success(pageInfo);
   }

    /**
     *
     * @param equipmentVo
     * @return
     */
    @ApiOperation("删除设备数据")
    @PostMapping("/deleteEquipment")
    @ResponseBody
    public ApiRestResponse deleteEquipmentById(@RequestBody EquipmentVo equipmentVo) {
        equipmentService.delete(equipmentVo);
        return new ApiRestResponse().success();
    }

    /**
     * 设备维修列表显示
     *
     * @return
     */
    /*@ApiOperation("设备维修列表显示")
    @GetMapping("/equipmentRepairList")
    public ApiRestResponse equipmentRepairList() {
    ComponentVo componentVo = equipmentService.equipmentRepairList();
    return new ApiRestResponse().success(componentVo);
    }*/

    /**
     * 查询实现
     *
     * @param equipment
     * @return
     */
    /*@ApiOperation("查询的实现")
    @PostMapping("/search")
    public ApiRestResponse search(@Valid @RequestBody Equipment equipment) {
        List<EquipmentVo> search = equipmentService.search(equipment);
        return new ApiRestResponse().success(search);
    }*/
}
