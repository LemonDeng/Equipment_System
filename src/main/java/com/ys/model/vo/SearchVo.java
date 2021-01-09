package com.ys.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchVo {
    @JsonProperty(value = "eWorkshop")
    private String eWorkshop;
    @JsonProperty(value = "eMachine")
    private String eMachine;
    @JsonProperty(value = "eName")
    private String eName;
    @JsonProperty(value = "cName")
    private String cName;

    public String geteWorkshop() {
        return eWorkshop;
    }

    public void seteWorkshop(String eWorkshop) {
        this.eWorkshop = eWorkshop;
    }

    public String geteMachine() {
        return eMachine;
    }

    public void seteMachine(String eMachine) {
        this.eMachine = eMachine;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

}
