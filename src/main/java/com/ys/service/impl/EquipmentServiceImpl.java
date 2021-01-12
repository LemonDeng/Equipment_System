package com.ys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ys.exception.YsLjException;
import com.ys.exception.YsLjExceptionEnum;
import com.ys.model.dao.EquipmentMapper;
import com.ys.model.dao.FactoryMapper;
import com.ys.model.pojo.Equipment;
import com.ys.model.pojo.Maintain;
import com.ys.model.request.ComponentChangeReq;
import com.ys.model.request.RepairSearchReq;
import com.ys.model.vo.BaseVo;
import com.ys.model.vo.ComponentVo;
import com.ys.model.vo.EquipmentVo;
import com.ys.model.pojo.Factory;
import com.ys.model.vo.SearchVo;
import com.ys.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 设备服务实现类
 */
@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    EquipmentMapper equipmentMapper;

    @Autowired
    FactoryMapper factoryMapper;

    /**
     * 添加设备信息
     * @param
     * @param
     * @param
     * @param
     * @param
     * @param
     */
    @Override
    public void addEquipment(EquipmentVo equipmentVo) {
        Factory factoryOld = factoryMapper.selectByFactoryName(equipmentVo.getfName());
        if (factoryOld != null) {
            int fid = factoryMapper.selectFid(equipmentVo.getfName(),"1");
            Equipment equipmentOld = equipmentMapper.selectByCode(equipmentVo.geteCode());
            if (equipmentOld != null) {
                throw new YsLjException(YsLjExceptionEnum.CODE_EXISTED);
            }
            equipmentMapper.insertEquipment(equipmentVo.geteWorkshop(), equipmentVo.geteMachine(),equipmentVo.geteName(), equipmentVo.geteCode(), equipmentVo.geteType(), fid);
        }
        else
        {
            Equipment equipmentOld = equipmentMapper.selectByCode(equipmentVo.geteCode());
            if (equipmentOld != null)
            {
                throw new YsLjException(YsLjExceptionEnum.CODE_EXISTED);
            }else {
                factoryMapper.insertE_FName(equipmentVo.getfName());
                int fid = factoryMapper.selectFid(equipmentVo.getfName(), "1");
                equipmentMapper.insertEquipment(equipmentVo.geteWorkshop(), equipmentVo.geteMachine(), equipmentVo.geteName(), equipmentVo.geteCode(), equipmentVo.geteType(), fid);
            }
        }

    }

    /**
     * 修改设备信息
     *
     */
    @Override
    public void updateEquipment(Equipment equipment) {
     /*   if (equipment.geteCode() != null)
        {
            Equipment equipmentOld = equipmentMapper.selectByCode(equipment.geteCode());
            if (equipmentOld != null)
            {
                throw new YsLjException(YsLjExceptionEnum.CODE_EXISTED);
            }
        }*/
        if (factoryMapper.selectByFactoryName(equipment.getE_fName()) != null)
        {
            int count = equipmentMapper.updateByPrimaryKeySelective(equipment);
            if (count == 0)
            {
                throw new YsLjException(YsLjExceptionEnum.UPDATE_FAILED);
            }
        }else
        {
            factoryMapper.insertE_FName(equipment.getE_fName());
        }
    }

    /*设备查询下拉列表*/
    @Override
    public List<SearchVo> equipmentSearchList() {
        return equipmentMapper.equipmentSearchList();
    }

    /**
     * 设备分页查询
     * @param repairSearchReq
     * @return
     */
  @Override
  public PageInfo allEquipmentList(RepairSearchReq repairSearchReq)
  {
      PageHelper.startPage(repairSearchReq.getPageNum(),repairSearchReq.getPageSize());
      List<Equipment> equipmentList = equipmentMapper.allEquipmentList(repairSearchReq);
      PageInfo pageInfo = new PageInfo(equipmentList);
      return pageInfo;
  }
    /**
     * 设备删除
     * @param equipmentVo
     */
    @Override
    public void delete(EquipmentVo equipmentVo) {
        equipmentMapper.delete(equipmentVo.geteId());
    }

    @Override
    public PageInfo allBaseList(RepairSearchReq repairSearchReq) {
        PageHelper.startPage(repairSearchReq.getPageNum(),repairSearchReq.getPageSize());
        List<BaseVo> componentVos = equipmentMapper.allBaseList(repairSearchReq);
        PageInfo pageInfo = new PageInfo(componentVos);
        return pageInfo;
    }

    @Override
    public int maintain(Maintain maintain) {
        return equipmentMapper.maintain(maintain);
    }

    @Override
    public void repair(ComponentChangeReq changeReq) {
        equipmentMapper.repair(changeReq);
    }

    @Override
    public int saveOldComponent(ComponentChangeReq changeReq) {
        return equipmentMapper.saveOldComponent(changeReq);
    }

    @Override
    public int updateComponent(ComponentChangeReq changeReq) {
        return equipmentMapper.updateComponent(changeReq);
    }


}
