package com.ys.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ys.exception.YsLjException;
import com.ys.exception.YsLjExceptionEnum;
import com.ys.model.dao.MaintainMapper;
import com.ys.model.dao.UserMapper;
import com.ys.model.pojo.Maintain;
import com.ys.model.pojo.User;
import com.ys.model.request.RepairSearchReq;
import com.ys.model.vo.MaintainDetailVo;
import com.ys.model.vo.SearchVo;
import com.ys.service.MaintainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintainServiceImpl implements MaintainService {
    @Autowired
    MaintainMapper maintainMapper;

    @Autowired
    UserMapper userMapper;


    /**
     *维护更新
     * @param maintain
     */
    @Override
    public void updateMaintain(Maintain maintain)
    {
        int count = userMapper.selectByUserName(maintain.getmPeopleName());
        if (count != 0)
        {
            maintain.setmPeopleId(count);
            maintainMapper.updateByPrimaryKeySelective(maintain);
        }else
        {
            throw new YsLjException(YsLjExceptionEnum.MAINTAIN_UPDATE_FAILED);
        }

    }



    @Override
    public void deleteMaintainById(Integer id) {
        maintainMapper.deleteMaintainById(id);
    }

    @Override
    public List<SearchVo> maintainSearchList() {
        return maintainMapper.maintainSearchList();
    }

    @Override
    public PageInfo search(RepairSearchReq repairSearchReq) {
        Page<Object> objects = PageHelper.startPage(repairSearchReq.getPageNum(), repairSearchReq.getPageSize());
        List<MaintainDetailVo> maintainList =   maintainMapper.search(repairSearchReq);
        PageInfo p = new PageInfo(maintainList);
        return p;
    }



    @Override
    public int getMaintainId(Integer eId) {
        return maintainMapper.getMaintainId(eId);
    }

    @Override
    public void maintainPictures(String p_name, Integer p_m_id) {
        maintainMapper.maintainPictures(p_name,p_m_id);
    }

    @Override
    public void equipmentMaintainContent(Integer eId, String content) {
        maintainMapper.equipmentMaintainContent(eId,content);
    }
}
