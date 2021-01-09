package com.ys.controller;

import com.github.pagehelper.PageInfo;
import com.ys.common.ApiRestResponse;
import com.ys.model.pojo.Maintain;
import com.ys.model.pojo.Repair;
import com.ys.model.request.ComponentChangeReq;
import com.ys.model.request.RepairSearchReq;
import com.ys.model.request.UpRepairReq;
import com.ys.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/index")
public class IndexController {
    @Autowired
    EquipmentService equipmentService;
    @Autowired
    MaintainService maintainService;
    @Autowired
    FactoryService factoryService;
    @Autowired
    ComponentService componentService;
    @PostMapping("/allBaseList")
    @ApiOperation("基本数据分页列表 和 根据零件名筛选")
    @ResponseBody
    public ApiRestResponse allBaseList(@Valid @RequestBody RepairSearchReq repairSearchReq) {
        PageInfo page = equipmentService.allBaseList(repairSearchReq);
        return ApiRestResponse.success(page);
    }


    @PostMapping("/maintain")
    @ApiOperation("维护:需要eId、mContent")
    @ResponseBody
    public ApiRestResponse maintain(@Valid @RequestBody Maintain maintain) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        maintain.setmTime(date);
        equipmentService.maintain(maintain);
        return ApiRestResponse.success();
    }

    @PostMapping("/repairORchange")
    @ApiOperation("维护和更换:flag=1就是维护，flag=2就是更换")
    @ResponseBody
    public ApiRestResponse repair(@Valid @RequestBody ComponentChangeReq changeReq) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        changeReq.setTime(date);
        if(changeReq.getFlag() == 1){//维护：只需添加维护内容
            equipmentService.repair(changeReq);
            return ApiRestResponse.success();
        }else{//否则，就更换
            //先根据传回的Cid查找出厂家名称
            int fId = componentService.getFidByCid(changeReq.getcId());
            String fName = factoryService.getFname(fId,2);
            //得到厂家名称后，把旧的零件信息和更新内容插入ys_change
            changeReq.setOldFactory(fName);
            equipmentService.saveOldComponent(changeReq);

//            //判断新的厂家名字是否存在
//            if(factoryService.getFid(changeReq.getNewFactory(),2)!=0){
//                //存在，则返回Fid
//                changeReq.setfId(factoryService.getFid(changeReq.getNewFactory(),2));
//            }else{
                //不存在，先新增新的厂家到ys_factory,set f_type = 2,再返回fId
                factoryService.insertCFName(changeReq.getNewFactory());
                changeReq.setfId(factoryService.getFid(changeReq.getNewFactory(),2));

//            }

            //最后把新的零件信息更新到ys_component
            equipmentService.updateComponent(changeReq);
        }
        return ApiRestResponse.success();
    }
}
