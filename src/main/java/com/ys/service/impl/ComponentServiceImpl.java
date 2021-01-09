package com.ys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ys.exception.YsLjException;
import com.ys.exception.YsLjExceptionEnum;
import com.ys.model.dao.ComponentMapper;
import com.ys.model.dao.EquipmentMapper;
import com.ys.model.dao.FactoryMapper;
import com.ys.model.pojo.Component;
import com.ys.model.vo.ComponentVo;
import com.ys.model.pojo.Factory;
import com.ys.model.request.RepairSearchReq;
import com.ys.model.vo.SearchVo;
import com.ys.service.ComponentService;
import com.ys.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 零件服务实现类
 */
@Service
public class ComponentServiceImpl implements ComponentService {
    @Autowired
    ComponentMapper componentMapper;

    @Autowired
    FactoryMapper factoryMapper;

    @Autowired
    EquipmentMapper equipmentMapper;

    /**
     * 新增零件
     */
    @Override
    public void addComponent(Component component)
    {

        Factory factoryOld = factoryMapper.selectByFactoryName(component.getfName());
        if (factoryOld != null) {
            int fid = factoryMapper.selectFid(component.getfName(),"2");
            Component componentOld = componentMapper.selectByCode(component.getcCode());
            if (componentOld != null) {
                throw new YsLjException(YsLjExceptionEnum.CODE_EXISTED);
            }
            componentMapper.insertComponent(
                    component.getcName(),
                    component.getcCode(),
                    component.getcType(),
                    component.getcLocation(),
                    component.getLifespan(),
                    component.getStarttime(),
                    component.geteId(),
                    fid);
        }
        else
        {
            Component componentOld = componentMapper.selectByCode(component.getcCode());
            if (componentOld != null)
            {
                throw new YsLjException(YsLjExceptionEnum.CODE_EXISTED);
            }else {
                factoryMapper.insertC_FName(component.getfName());
                int fid = factoryMapper.selectFid(component.getfName(), "2");
                componentMapper.insertComponent(component.getcName(),
                        component.getcCode(),
                        component.getcType(),
                        component.getcLocation(),
                        component.getLifespan(),
                        component.getStarttime(),
                        component.geteId(),fid);
            }
        }
    }
    /**
     * 修改零件信息
     *
     */
    @Override
    public void updateComponent(Component component) {
        if (component.getcCode() != null)
        {
            Component componentOld = componentMapper.selectByCode(component.getcCode());
            if (componentOld != null)
            {
                throw new YsLjException(YsLjExceptionEnum.CODE_EXISTED);
            }
        }
        if (factoryMapper.selectByFactoryName(component.getfName()) != null)
        {
            int count = componentMapper.updateByPrimaryKeySelective(component);
            if (count == 0)
            {
                throw new YsLjException(YsLjExceptionEnum.UPDATE_FAILED);
            }
        }else
        {
            factoryMapper.insertC_FName(component.getfName());
        }
    }

    /****************************/
    /**
     * 分页查询零件
     * @param repairSearchReq
     * @return
     */
    @Override
    public PageInfo allComponentList(RepairSearchReq repairSearchReq) {
        PageHelper.startPage(repairSearchReq.getPageNum(),repairSearchReq.getPageSize());
        List<ComponentVo> componentVos = componentMapper.allList(repairSearchReq);

        componentVos.stream().forEach(componentVo -> {
            long now = new Date().getTime();
            long start = componentVo.getStarttime().getTime();

            String useTime = DateUtils.formatDateTime((now - start) / 1000);

            componentVo.setRuntime(useTime);

        });

        PageInfo p = new PageInfo(componentVos);
        return p;
    }

    @Override
    public List<SearchVo> componentSearchList() {
        return componentMapper.componentSearchList();
    }

    /**
     * 删除
     * @param componentVo
     */
    @Override
    public void delete(ComponentVo componentVo) {
        Component componentOld = componentMapper.selectByCode(componentVo.getcCode());
        if (componentOld == null)
        {
            throw new YsLjException(YsLjExceptionEnum.DELETE_COMPONENT_FAILED);
        }
        else
        {
            componentMapper.delete(componentVo.getcCode());
        }

    }

    @Override
    public int getFidByCid(int cId) {

        return componentMapper.getFidByCid(cId);
    }
}
