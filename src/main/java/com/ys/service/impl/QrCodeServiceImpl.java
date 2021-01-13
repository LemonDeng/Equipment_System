package com.ys.service.impl;

import com.ys.model.dao.ComponentMapper;
import com.ys.model.pojo.Component;
import com.ys.model.vo.ComponentVo;
import com.ys.service.ComponentService;
import com.ys.service.QrCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class QrCodeServiceImpl implements QrCodeService {


    @Autowired
    ComponentMapper componentMapper;


    @Override
    public Component qrCodeImp(Component component) {
        List<Component> components = componentMapper.qrCode(component.geteId());
        if (components==null || components.size() == 0 ){
            return null;
        }
        return components.get(0);
    }


}
