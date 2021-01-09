package com.ys.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ys.exception.YsLjException;
import com.ys.exception.YsLjExceptionEnum;
import com.ys.model.dao.RepairMapper;
import com.ys.model.dao.UserMapper;
import com.ys.model.pojo.Repair;
import com.ys.model.pojo.User;
import com.ys.model.request.RepairSearchReq;
import com.ys.model.vo.RepairDetailVo;
import com.ys.model.vo.SearchVo;
import com.ys.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairServiceImpl implements RepairService {

    @Autowired
    RepairMapper repairMapper;

    @Autowired
    UserMapper userMapper;

    /**
     * 维修更新
     * @param repair
     */
    @Override
    public void updateRepair(Repair repair)
    {
        int count = userMapper.selectByUserName(repair.getrPeopleName());
        if (count != 0)
        {
            repair.setrPeopleId(count);
            repairMapper.updateByEidSelective(repair);
        }
        else
        {
            throw new YsLjException(YsLjExceptionEnum.REPAIR_UPDATE_FAILED);
        }
    }


    @Override
    public void deleteRepairById(Integer id) {
        repairMapper.deleteRepairById(id);
    }

    @Override
    public List<SearchVo> repairSearchList() {
        return repairMapper.repairSearchList();
    }



    @Override
    public PageInfo search(RepairSearchReq repairSearchReq) {
        Page<Object> objects = PageHelper.startPage(repairSearchReq.getPageNum(), repairSearchReq.getPageSize());
        List<RepairDetailVo> search = repairMapper.search(repairSearchReq);
        PageInfo p = new PageInfo(search);
        return p;
    }



    @Override
    public void equipmentRepairContent(Integer eId, String content) {
        repairMapper.equipmentRepairContent(eId,content);
    }


    @Override
    public int getRepairId(Integer eId) {
        return repairMapper.getRepairId(eId);
    }

    @Override
    public void repairPictures(String p_name, Integer p_r_id) {
        repairMapper.repairPictures(p_name,p_r_id);
    }

}
