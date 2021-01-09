package com.ys.exception;

public enum YsLjExceptionEnum {
    NEED_WORK_NUMBER(10001, "工号必须由10位数字和字母组成"),
    NEED_PASSWORD(10002, "密码不能小于8位"),
    PASSWORD_TOO_SHORT(10003, "密码长度不能小于8位"),
    WORKNUMBER_EXISTED(10004, "工号已经存在"),
    INSERT_FAILED(10005, "插入失败，请重试"),
    WRONG_PASSWORD(10006, "密码错误"),
    NEED_LOGIN(10007, "用户未登录"),
    UPDATE_FAILED(10008, "更新失败"),
    NEED_ADMIN(10009, "无管理员权限"),
    PARA_NOT_NULL(10010, "参数不能为空"),
    CREATE_FAILED(10011, "新增失败"),
    REQUEST_PARAM_ERROR(10012, "参数错误"),
    DELETE_FAILED(10013, "删除失败,当前用户不存在"),
    NEED_USER_ADDRESS(10014,"地址不能为空"),
    NEED_USER_PHONE(10015,"手机号为11位的全数字"),
    NEED_USER_EMAIL(10016,"邮箱不能为空"),
    CPACHA_IS_NULL(10017,"验证码为空"),
    PASS_LENGTH(10019,"工号不够10位"),
    NEED_USER_NAME(10020,"用户名不能为空"),
    NEED_USER_WORKNUMBER(10021,"用户名不存在,或密码错误"),
    NAME_EXISTED(10022,"不允许重名"),
    COMPANY_EXISTED(10023,"公司名已经存在"),
    CODE_EXISTED(10023,"设备编码已经存在"),
    ECODE_EXISTED(10024,"设备编码由11位数字组成"),
    PEOPLE_NOT_EXISTED(10025,"该人员不存在,请重新输入"),
    DELETE_COMPONENT_FAILED(10026, "删除失败,当前零件不存在"),
    MAINTAIN_UPDATE_FAILED(10028, "更新失败,维护人员不存在"),
    REPAIR_UPDATE_FAILED(10029, "更新失败,维修人员不存在"),
    SYSTEM_ERROR(20000, "系统异常，请从控制台或日志中查看具体错误信息");
    /**
     * 异常码
     */
    Integer code;
    /**
     * 异常信息
     */
    String msg;

    YsLjExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
