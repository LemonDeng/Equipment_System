package com.ys.service;

import com.github.pagehelper.PageInfo;
import com.ys.exception.YsLjException;
import com.ys.model.pojo.User;
import com.ys.model.pojo.UserVo;
import com.ys.model.request.AddUserReq;
import com.ys.model.vo.UserLoginVO;

import java.util.List;

/**
 * 描述：UserService
 */
public interface UserService {



    //登录
   // User login(UserVo userVo) throws YsLjException;

    //登录
    User login(UserVo userVo) throws YsLjException;

    //登录
    //User login(UserLoginVO userLoginVO) throws YsLjException;

    //增加用户
    void addUser(AddUserReq addUserReq);


    //删除用户
    void deleteUser(UserVo userVo) throws YsLjException;


    boolean checkAdminRole(User user);


    void updateInformation(User updateUserReq);

    //PageInfo listForAmin(UserVo);

    //分页查询用户
    PageInfo listForAmin(UserVo userVo);

    List<User> getAllUser();
/*筛选查询*/
    User findByName(User user);

    /*******************/
    /*得到所有人员*/
    //List<String> peopleList();


}
