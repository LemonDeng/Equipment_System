package com.ys.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ys.exception.YsLjException;
import com.ys.exception.YsLjExceptionEnum;
import com.ys.model.dao.ChangeMapper;
import com.ys.model.dao.ComponentMapper;
import com.ys.model.dao.FactoryMapper;
import com.ys.model.dao.UserMapper;
import com.ys.model.pojo.*;
import com.ys.model.request.RepairSearchReq;
import com.ys.model.vo.ChangeDetailVo;
import com.ys.model.vo.SearchVo;
import com.ys.service.ChangeService;
import com.ys.util.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class ChangeServiceImpl implements ChangeService {

    @Autowired
    ChangeMapper changeMapper;

    @Autowired
    ComponentMapper componentMapper;

    @Autowired
    FactoryMapper factoryMapper;

    @Autowired
    UserMapper userMapper;

    /**
     * 更换更新
     * @param changevo
     */
    @Override
    public void updateChange(ChangeVo changevo)
    {

        User userOld = userMapper.selectByName(changevo.getChangePeople());

        if (userOld != null) {

            int id = userOld.getuId();

            Component oldComonet = componentMapper.selectByPrimaryKey(changevo.getCid());

            if (oldComonet == null) {
                throw  new YsLjException(YsLjExceptionEnum.UPDATE_FAILED);
            }

            Component newComonet = new Component();
            newComonet.setcId(changevo.getCid());
            newComonet.setcName(changevo.getNewCname());
            newComonet.setfName(changevo.getNewCfactory());
            newComonet.setcCode(changevo.getNewCcode());
            newComonet.setcType(changevo.getNewCtype());
            newComonet.setStarttime(changevo.getStartTime());

            Factory factory = factoryMapper.selectByFactoryName(changevo.getNewCfactory());

            if (factory == null) {
                factoryMapper.insertC_FName(changevo.getNewCfactory());

                Factory f  = factoryMapper.selectByFactoryName(changevo.getNewCfactory());
                newComonet.setfId(f.getfId());
                newComonet.setcLocation(changevo.getcLocation());
                componentMapper.updateBySelective(newComonet);

                Change change = new Change();
                change.setcPeopleId(id);
                change.setcTime(changevo.getStartTime());
                change.setCcId(changevo.getCid());
                change.setOcCode(oldComonet.getcCode());
                change.setOcFactory(f.getfName());
                change.setOcName(oldComonet.getcName());
                change.setOcType(oldComonet.getcType());
                change.setcLocation(changevo.getcLocation());

                StringBuilder changContent = new StringBuilder();
                if (!"".equals(changevo.getNewCname()) && null != changevo.getNewCname()) {
                    changContent.append("零件名字由").append(oldComonet.getcName()).append("修改为").append(changevo.getNewCname());
                }
                if (!"".equals(changevo.getNewCfactory()) && null != changevo.getNewCfactory()) {
                    changContent.append("零件工厂由").append(oldComonet.getcName()).append("修改为").append(changevo.getNewCfactory());
                }
                if (!"".equals(changevo.getNewCcode()) && null != changevo.getNewCcode()) {
                    changContent.append("零件编码由").append(oldComonet.getcCode()).append("修改为").append(changevo.getNewCcode());
                }
                if (!"".equals(changevo.getNewCtype()) && null != changevo.getNewCtype()) {
                    changContent.append("零件类型由").append(oldComonet.getcType()).append("修改为").append(changevo.getNewCtype());
                }

                change.setcContent(changContent.toString());
                changeMapper.insert(change);

                //throw new YsLjException(YsLjExceptionEnum.FOCTORY_UPDATE_FAILED);
            }else {
                Factory oldFactory = factoryMapper.selectByPrimaryKey(oldComonet.getfId());
                newComonet.setfId(factory.getfId());
                newComonet.setcLocation(changevo.getcLocation());
                componentMapper.updateBySelective(newComonet);

                Change change = new Change();
                change.setcPeopleId(id);
                change.setcTime(changevo.getStartTime());
                change.setCcId(changevo.getCid());
                change.setOcCode(oldComonet.getcCode());
                change.setOcFactory(oldFactory.getfName());
                change.setOcName(oldComonet.getcName());
                change.setOcType(oldComonet.getcType());
                change.setcLocation(changevo.getcLocation());

                StringBuilder changContent = new StringBuilder();
                if (!"".equals(changevo.getNewCname()) && null != changevo.getNewCname()) {
                    changContent.append("零件名字由").append(oldComonet.getcName()).append("修改为").append(changevo.getNewCname());
                }
                if (!"".equals(changevo.getNewCfactory()) && null != changevo.getNewCfactory()) {
                    changContent.append("零件工厂由").append(oldComonet.getcName()).append("修改为").append(changevo.getNewCfactory());
                }
                if (!"".equals(changevo.getNewCcode()) && null != changevo.getNewCcode()) {
                    changContent.append("零件编码由").append(oldComonet.getcCode()).append("修改为").append(changevo.getNewCcode());
                }
                if (!"".equals(changevo.getNewCtype()) && null != changevo.getNewCtype()) {
                    changContent.append("零件类型由").append(oldComonet.getcType()).append("修改为").append(changevo.getNewCtype());
                }

                change.setcContent(changContent.toString());
                changeMapper.insert(change);
            }

        } else {
            throw new YsLjException(YsLjExceptionEnum.UPDATE_FAILED);
        }

    }

    /*I***************************************/


    @Override
    public List<SearchVo> changeSearchList() {
        List<SearchVo> searchVos = new ArrayList<>();
        return changeMapper.changeSearchList();
    }


    @Override
    public void deleteById(Integer id) {
        changeMapper.deleteById(id);
    }

    @Override
    public PageInfo search(RepairSearchReq repairSearchReq) {
        PageHelper.startPage(repairSearchReq.getPageNum(),repairSearchReq.getPageSize());
        List<ChangeDetailVo> searchList = changeMapper.search(repairSearchReq);
        PageInfo p = new PageInfo(searchList);
        return p;
    }


}
