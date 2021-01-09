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
    @ApiOperation("维修更新")
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


    @ApiOperation("设备维修内容和图片上传")
    @PostMapping("/equipmentRepairContentAndImage")
    public ApiRestResponse equipmentRepairContentAndImage(@RequestParam("e_id")Integer eId,  @RequestParam("r_content") String content, @RequestParam("p_name") String iamgeName){
        repairService.equipmentRepairContent(eId,content);
        Integer p_r_id = repairService.getRepairId(eId);
        repairService.repairPictures(iamgeName,p_r_id);
        return new ApiRestResponse().success();
    }

    /**
     * 图片上传
     */
//    @ApiOperation("设备维修内容和图片上传")
//    @RequestMapping(value = "/equipmentRepairContentAndImage",method = RequestMethod.POST)
//    @ResponseBody
//    public ApiRestResponse uploadImage(@RequestParam("e_id")Integer eId,  @RequestParam("r_content") String content,@RequestParam(value = "avatar") MultipartFile avatar){
//
//        Map<String,Object> map = new HashMap<>();
//        if (avatar.isEmpty()) {
//            return ApiRestResponse.error(400,"图片为空");
//        }else {
//
//            //保存时的文件名
//            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//            Calendar calendar = Calendar.getInstance();
//            String dateName = df.format(calendar.getTime())+avatar.getOriginalFilename();
//
//            //保存文件的绝对路径
//            WebApplicationContext webApplicationContext = (WebApplicationContext) SpringContextUtils.getApplicationContext();
//            ServletContext servletContext = webApplicationContext.getServletContext();
//            String realPath = servletContext.getRealPath("/");
//
//            String filePath = realPath + "WEB-INF"+ File.separator + "classes" + File.separator +"static" + File.separator + "resource" + File.separator+dateName;//此路径是放在tomcat war包的绝对路径
//            File newFile = new File(filePath);
//            System.out.println("filePath=:"+filePath);
//            //MultipartFile的方法直接写文件
//            try {
//
//                //上传文件
//                avatar.transferTo(newFile);
//
//                //数据库存储的相对路径
//                String projectPath = servletContext.getContextPath();
//                HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
//                String contextpath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+projectPath;
//                String url = contextpath + "/resource/"+dateName;//此路径是放在tomcat war包的相对路径
//                //文件名与文件URL存入数据库表
//                System.out.println("url=:"+url);
//                map.put("imageUrl",url);//返回前台
//
//                /*dao层*/
//                repairService.equipmentRepairContent(eId,content);
//                Integer p_r_id = repairService.getRepairId(eId);
//                repairService.repairPictures(url,p_r_id);
//            } catch (IllegalStateException  | IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return ApiRestResponse.success(map);
//    }

}
