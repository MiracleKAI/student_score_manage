package com.miracle.studentscoremanage.util;

/**
 * @author miracle
 */
public class UiReturn {
    private String desc;
    private Object data;

    private UiReturn(String desc, Object data) {
        this.desc = desc;
        this.data = data;
    }

    public UiReturn(String desc) {

        this.desc = desc;
    }


    public UiReturn() {
    }


    public static UiReturn ok(Object data) {
        return new UiReturn("success", data);
    }


    public static UiReturn notOk(String desc) {
        return new UiReturn(desc, null);
    }


    public static UiReturn notOk(String desc, Object data) {
        return new UiReturn(desc, data);
    }


    public String getDesc() {
        return this.desc;
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }


    public Object getData() {
        return this.data;
    }


    public void setData(Object data) {
        this.data = data;
    }
}
