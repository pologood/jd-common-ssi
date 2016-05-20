package com.jd.common.ssi.commond;

import java.util.Map;

/**
 * SSI指令接口
 * Created by caozhifei on 2016/5/19.
 */
public interface SSICommand {
    /**
     * 根据传入的指令参数map进行解析执行处理，并返回对应处理结果
     * @param paraMap
     * @return
     */
    public String execute(Map<String,String> paraMap);
}
