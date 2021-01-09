package com.ys.model.myjson;

/**
 * 控制器响应结果实体类，返回接口调用信息描述
 */
public class ResponseJsonMessage {

    private static final long serialVersionUID = -7127875856370230011L;

    /**
     * 状态码
     */

    /**
     * 数据
     */
    private Object data;

    public ResponseJsonMessage () {
    }

    public ResponseJsonMessage (Object data) {
        this.data = data;
    }

    /**此处省略变量的set和get方法**/
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
