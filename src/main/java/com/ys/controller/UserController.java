package com.ys.controller;

import com.github.pagehelper.PageInfo;
import com.ys.common.ApiRestResponse;
import com.ys.common.Constant;
import com.ys.exception.YsLjException;
import com.ys.exception.YsLjExceptionEnum;
import com.ys.model.pojo.User;
import com.ys.model.pojo.UserVo;
import com.ys.model.request.AddUserReq;
import com.ys.model.request.UpdateUserReq;
import com.ys.model.vo.ComponentVo;
import com.ys.model.vo.UserLoginVO;
import com.ys.service.UserService;
import com.ys.service.impl.TokenService;
import com.ys.util.CpachaUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * 描述 : 用户控制器
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    /**
     *登录
     * @param userVo
     * @param session
     * @return
     * @throws YsLjException
     */
    @PostMapping("/login")
    @ResponseBody
    @ApiOperation(value = "登录",notes = "uWorknumber:工号(require)   uPassword:密码 (require)")

    public ApiRestResponse login(@RequestBody UserVo userVo, HttpSession session,HttpServletResponse response)
            throws YsLjException {
        if (StringUtils.isEmpty(userVo.getuWorknumber())) {
            return ApiRestResponse.error(YsLjExceptionEnum.NEED_USER_NAME);
        }
        if (StringUtils.isEmpty(userVo.getuPassword())) {
            return ApiRestResponse.error(YsLjExceptionEnum.NEED_PASSWORD);
        }
        User user = userService.login(userVo);
        String token = tokenService.getToken(userVo);
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        response.addCookie(cookie);
        //保存用户信息时，不保存密码
        user.setuPassword(null);
        session.setAttribute(token, user);
        return ApiRestResponse.success(user);
    }


    /**
     * 退出
     * @param session
     * @param request
     * @return
     */
    @PostMapping("logout")
    @ResponseBody
    public ApiRestResponse logout(HttpSession session,HttpServletRequest request) {
        String token  = "";

        for (Cookie cookie : request.getCookies())
            if (cookie.getName().equals("token")) {
                token = cookie.getValue();
                break;
            }

        session.removeAttribute(token);
        return ApiRestResponse.success();
    }

    /**
     * 添加用户
     * @param
     * @return
     * @throws YsLjException
     */
    @ApiOperation("添加用户")
    @PostMapping("/addUser")
    @ResponseBody
    public ApiRestResponse addUser(HttpSession session,@Valid @RequestBody AddUserReq addUserReq,HttpServletRequest request)
            throws YsLjException {

        String token  = "";

        for (Cookie cookie : request.getCookies())
            if (cookie.getName().equals("token")) {
                token = cookie.getValue();
                break;
            }
        User currentUser = (User) session.getAttribute(token);
        if (currentUser == null)
        {
            return ApiRestResponse.error(YsLjExceptionEnum.NEED_LOGIN);
        }

        //校验是否是超级管理员
        boolean adminRole = userService.superAdmin(currentUser);
        if (adminRole) {
            //是超级管理员，执行操作
           userService.addUser(addUserReq);
            return ApiRestResponse.success();
        }else
        {
            return ApiRestResponse.error(YsLjExceptionEnum.NEED_ADMIN);
        }

    }

    /**
     * 删除用户
     * @return
     */
    @PostMapping("/deleteUser")
    @ResponseBody
    public ApiRestResponse deleteCategory(@RequestBody User user,HttpServletRequest request,HttpSession session) {

        String token  = "";

        for (Cookie cookie : request.getCookies())
            if (cookie.getName().equals("token")) {
                token = cookie.getValue();
                break;
            }
        User currentUser = (User) session.getAttribute(token);
        if (currentUser == null)
        {
            return ApiRestResponse.error(YsLjExceptionEnum.NEED_LOGIN);
        }
        //校验是否是超级管理员
        boolean adminRole = userService.superAdmin(currentUser);
        if (adminRole) {
            //是超级管理员，执行操作
            userService.deleteUser(user);
            return ApiRestResponse.success();
        }
         return ApiRestResponse.error(YsLjExceptionEnum.NEED_SURPER_ADMIN);
    }

    /**
     * 用户分页查询
     * @param
     * @param
     * @return
     */

    @ApiOperation("用户分页查询")
    @PostMapping("/listUser")
    @ResponseBody
    public ApiRestResponse listUserForAdmin(@RequestBody UserVo userVo) {
        PageInfo pageInfo = userService.listForAmin(userVo);
        return ApiRestResponse.success(pageInfo);
    }


    /**
     * 修改用户
     */
    @ApiOperation("修改用户")
    @PostMapping("/updateUser")
    @ResponseBody
    public ApiRestResponse updateUser(@Valid @RequestBody UpdateUserReq updateUserReq, HttpSession session,HttpServletRequest request) {

        String token  = "";

        for (Cookie cookie : request.getCookies())
            if (cookie.getName().equals("token")) {
                token = cookie.getValue();
                break;
            }

        User currentUser = (User) session.getAttribute(token);
        if (currentUser == null)
        {
            return ApiRestResponse.error(YsLjExceptionEnum.NEED_LOGIN);
        }

        //校验是否是超级管理员
        boolean adminRole = userService.superAdmin(currentUser);
        if (adminRole) {
            //是超级管理员，执行操作
            User user = new User();
            BeanUtils.copyProperties(updateUserReq,user);
            userService.updateInformation(user);
            return ApiRestResponse.success();
        }else
        {
            return ApiRestResponse.error(YsLjExceptionEnum.NEED_ADMIN);
        }
    }

    //验证码
    @GetMapping("/cpa")
    @ResponseBody
    @CrossOrigin
    public ApiRestResponse getCpacha(HttpServletRequest request, HttpServletResponse response)
    {
        CpachaUtil cpachaUtil = new CpachaUtil();
        String generatorVCode = cpachaUtil.generatorVCode();
        return ApiRestResponse.success(generatorVCode);
    }


    /**
     * 筛选查询用户
     * @param user
     * @return
     */
    @ApiOperation("筛选查询用户")
    @PostMapping("/getUser")
    @ResponseBody
    public ApiRestResponse getUser(@RequestBody User user)
    {
        User currentUser = userService.findByName(user);
        return ApiRestResponse.success(currentUser);
    }

}







