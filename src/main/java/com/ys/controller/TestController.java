package com.ys.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ys.common.ApiRestResponse;
import com.ys.model.myjson.ResponseJsonMessage;
import com.ys.model.myjson.ResponseJsonStatus;
import com.ys.model.myjson.SystemUser;
import com.ys.model.vo.SearchVo;
import com.ys.service.ChangeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jsonFormat3")
public class TestController {
    @Autowired
    ChangeService changeService;
/*    @GetMapping("/list")
    public ResponseJsonMessage getUserList() {
        List<SystemUser> list = new ArrayList<>();
        SystemUser xiaoming = new SystemUser("1", "小明", "xiaoming123");
        SystemUser xiaoli = new SystemUser("2", "小丽", "xiaoli123");
        list.add(xiaoming);
        list.add(xiaoli);





        return new ResponseJsonMessage(json);
    }*/

    /*维修查询列表*/
    @GetMapping("/searchList")
    @ApiOperation("更换查询列表")
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
}
