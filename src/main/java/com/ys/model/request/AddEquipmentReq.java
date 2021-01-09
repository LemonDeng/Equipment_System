package com.ys.model.request;

import javax.validation.constraints.NotNull;

public class AddEquipmentReq {

    @NotNull(message = "车间号不能为null")
    private String eWorkshop;

    @NotNull(message = "机台号不能为null")
    private String eMachine;

    @NotNull(message = "设备名称不能为null")
    private String eName;

    @NotNull(message = "设备编码不能为null")
    private String eCode;

    @NotNull(message = "设备型号不能为null")
    private String eType;

    //@NotNull(message = "厂家编号不能为null")
    private Integer fId;

    private String f_name;

    private String f_address;

    private String f_type;

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getF_address() {
        return f_address;
    }

    public void setF_address(String f_address) {
        this.f_address = f_address;
    }

    public String getF_type() {
        return f_type;
    }

    public void setF_type(String f_type) {
        this.f_type = f_type;
    }

    public String geteWorkshop() {
        return eWorkshop;
    }

    public void seteWorkshop(String eWorkshop) {
        this.eWorkshop = eWorkshop == null ? null : eWorkshop.trim();
    }

    public String geteMachine() {
        return eMachine;
    }

    public void seteMachine(String eMachine) {
        this.eMachine = eMachine == null ? null : eMachine.trim();
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName == null ? null : eName.trim();
    }

    public String geteCode() {
        return eCode;
    }

    public void seteCode(String eCode) {
        this.eCode = eCode == null ? null : eCode.trim();
    }

    public String geteType() {
        return eType;
    }

    public void seteType(String eType) {
        this.eType = eType == null ? null : eType.trim();
    }

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }
}