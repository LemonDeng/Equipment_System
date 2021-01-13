package com.ys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ys.exception.YsLjException;
import com.ys.exception.YsLjExceptionEnum;
import com.ys.model.dao.UserMapper;
import com.ys.model.pojo.User;
import com.ys.model.pojo.UserVo;
import com.ys.model.request.AddUserReq;
import com.ys.model.vo.UserLoginVO;
import com.ys.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述：UserService实现类
 */

@Service
public class UserServiceImpl  implements UserService {
    @Autowired
    UserMapper userMapper;

    //登录
    @Override
    public User login(UserVo userVo) throws YsLjException {

        User user = userMapper.selectLogin(userVo.getuWorknumber(), userVo.getuPassword());
        if (user == null) {
            throw new YsLjException(YsLjExceptionEnum.NEED_USER_WORKNUMBER);
        }
        return user;
    }


    //增加用户
    @Override
    public void addUser(AddUserReq addUserReq) {

        User user = new User();
        BeanUtils.copyProperties(addUserReq,user);
        User userOld = userMapper.selectByName(addUserReq.getuName());
        if (userOld != null) {
            throw new YsLjException(YsLjExceptionEnum.NAME_EXISTED);
        }
        User worknumber = userMapper.selectByWorknumber(addUserReq.getuWorknumber());
        if (worknumber != null) {
            throw new YsLjException(YsLjExceptionEnum.WORKNUMBER_EXISTED);
        }
        int count = userMapper.insertSelective(user);
        if (count == 0) {
            throw new YsLjException(YsLjExceptionEnum.CREATE_FAILED);
        }

    }

    //删除用户
    @Override
    public void deleteUser(UserVo userVo) throws YsLjException {
        User userOld = userMapper.selectByWorknumber(userVo.getuWorknumber());
        //查找不到记录，无法删除,删除失败
        if (userOld == null) {
            throw new YsLjException((YsLjExceptionEnum.DELETE_FAILED));
        }
        int count = userMapper.deleteByPrimaryKey(userOld.getuId());
        if (count == 0) {
            throw new YsLjException(YsLjExceptionEnum.DELETE_FAILED);
        }

    }

    @Override
    public boolean checkAdminRole(User user) {
        //1是普通用户，2是管理员
        return user.getIsadmin().equals(2);
    }

    //修改用户
    @Override
    public void updateInformation(User updateUserReq) {
        if (updateUserReq.getuName() != null)
        {
            User userOld = userMapper.selectByName(updateUserReq.getuName());
            if (userOld != null)
            {
                throw new YsLjException(YsLjExceptionEnum.NAME_EXISTED);
            }
        }
        int count = userMapper.updateByPrimaryKeySelective(updateUserReq);
        if (count == 0)
        {
            throw new YsLjException(YsLjExceptionEnum.UPDATE_FAILED);
        }
    }

    //分页查询用户
    @Override
    public PageInfo listForAmin(UserVo userVo) {
        PageHelper.startPage(userVo.getPageNum(), userVo.getPageSize());
        List<User> userList = userMapper.selectList();
        PageInfo pageInfo = new PageInfo(userList);
        return pageInfo;
    }
//查询所有用户
    @Override
    public List<User> getAllUser()
    {
        List<User> cuurentUser = userMapper.selectList();
        if (cuurentUser == null)
        {
            throw new YsLjException(YsLjExceptionEnum.NEED_USER_WORKNUMBER);
        }
        return cuurentUser;
    }

    /**
     * 筛选查询
     * @param user
     * @return
     */
    @Override
    public User findByName(User user)
    {
        /*通过姓名查询*/
        User user1 = userMapper.selectByName(user.getuName());
        /*通过工号查询*/
        User user2 = userMapper.selectByWorknumber(user.getuWorknumber());
        /*通过姓名和工号一起查询*/
        User user3 = userMapper.selectByNameAndWorkNumber(user.getuName(), user.getuWorknumber());
        if (user1 != null)
        {
            return user1;
        }else if (user2 !=null)
        {
            return user2;
        }else if (user3 != null)
        {
            return user3;
        }else
        {
            throw new YsLjException(YsLjExceptionEnum.NOT_PEOPLE);
        }
    }


}



