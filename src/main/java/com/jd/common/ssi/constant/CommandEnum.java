package com.jd.common.ssi.constant;

/**
 * Created by caozhifei on 2016/5/19.
 */
public enum CommandEnum {
    COMMAND_PREFIX("<!--#"),
    COMMAND_SUFFIX("-->");
    private String desc;

    CommandEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
