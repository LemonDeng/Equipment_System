package com.ys.model.dao;

import com.ys.model.pojo.Picture;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureMapper {
    int deleteByPrimaryKey(Integer pId);

    int insert(Picture record);

    int insertSelective(Picture record);

    Picture selectByPrimaryKey(Integer pId);

    int updateByPrimaryKeySelective(Picture record);

    int updateByPrimaryKey(Picture record);
}