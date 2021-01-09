package com.ys.model.dao;

import com.ys.model.pojo.User;
import com.ys.model.pojo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer uId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByWorknumber(String uWorknumber);

    User selectByName(String uName);

    User selectByNameAndWorkNumber(@Param("uName") String uName,@Param("uWorknumber") String uWorknumber);

    User selectLogin(@Param("uWorknumber") String uWorknumber, @Param("uPassword")String uPassword);

    List<User> selectList();

    int selectByUserName(String uName);


    //获取用户所有信息
    User selectBypassword(@Param("uPassword") String uPassword,@Param("uName") String uName);
    //    修改密码
    int updateByKeySelective(User record);

}