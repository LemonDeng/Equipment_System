package com.ys.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.ys.common.ApiRestResponse;
import com.ys.model.pojo.Maintain;
import com.ys.model.request.ComponentChangeReq;
import com.ys.model.request.RepairSearchReq;
import com.ys.model.vo.SearchVo;
import com.ys.service.ComponentService;
import com.ys.service.EquipmentService;
import com.ys.service.FactoryService;
import com.ys.service.MaintainService;
import com.ys.service.impl.PicturesUploadServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Autowired
    PicturesUploadServiceImpl picturesUploadService;
    @PostMapping("/allBaseList")
    @ApiOperation("基本数据分页列表 和 根据零件名筛选")
    @ResponseBody
    public ApiRestResponse allBaseList(@Valid @RequestBody RepairSearchReq repairSearchReq) {
        PageInfo page = equipmentService.allBaseList(repairSearchReq);
        return ApiRestResponse.success(page);
    }

    @GetMapping("/allCName")
    @ApiOperation(value = "零件名下列列表")
    @ResponseBody
    public ApiRestResponse allCName() {
        List<SearchVo> cNameVo = componentService.allCName();
        JSONObject object = new JSONObject();
        //将机台、设备等对应放进一个list集合
        List<String> cNames =  new ArrayList<>();
        for(SearchVo searchVo : cNameVo){
            cNames.add(searchVo.getcName());
        }
        object.put("cNames",cNames);
        return ApiRestResponse.success(object);
    }

    @PostMapping("/maintain")
    @ApiOperation(value = "维护" ,notes = "eId设备id（require）、mContent维护内容（require）")
    @ResponseBody  //@Valid @RequestBody Maintain maintain,
    public ApiRestResponse maintain(@RequestParam("file") MultipartFile[] file, @RequestParam("eId") int eId, @RequestParam("mContent") String mContent) throws Exception{
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        /*maintain.setmTime(date);
        //添加维护内容
        equipmentService.maintain(maintain);
        //根据维护内容得到维护id
        int mId = maintainService.getMaintainId(maintain.getmContent());

        //通过维护id作为外键插进picture表
        String [] n = picturesUploadService.add(file);
        for (String nn:n) {
            maintainService.maintainPictures(nn,mId);
        }*/

        //单个参数传
        Maintain maintain = new Maintain();
        maintain.seteId(eId);
        maintain.setmContent(mContent);
        maintain.setmTime(date);
        //添加维护内容
        equipmentService.maintain(maintain);
        //根据维护内容得到维护id
        Date mTime = maintain.getmTime();
        int mId = maintainService.getMaintainId(maintain);

        //通过维护id作为外键插进picture表
        String [] n = picturesUploadService.add(file);
        for (String nn:n) {
            maintainService.maintainPictures(nn,mId);
        }
        return ApiRestResponse.success();
    }

    @PostMapping("/repairORchange")
    @ApiOperation("维护和更换:flag=1就是维护，flag=2就是更换")
    @ResponseBody
    public ApiRestResponse repair(@RequestParam("file") MultipartFile[] file, @Valid @RequestBody ComponentChangeReq changeReq) throws Exception{
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

    @PostMapping("/uploadImage")
    public ApiRestResponse add(@RequestParam("file") MultipartFile[] file) throws Exception{
        String [] n = picturesUploadService.add(file);
        for (String nn:n) {
            System.out.println(nn);
        }
        return ApiRestResponse.success();
    }
}
