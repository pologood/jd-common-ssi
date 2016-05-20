package com.jd.common.ssi.processor;

import com.jd.common.ssi.commond.SSICommand;

import java.util.Map;

/**
 * Created by caozhifei on 2016/5/19.
 */
public interface SSIFinder {
    /**
     * 查找指令行中包含的ssi指令
     *
     * @param command
     * @return
     */
    SSICommand findCommand(String command);

    /**
     * 查找指令中包含的参数配置
     *
     * @param command
     * @return
     */
    Map<String, String> findPara(String command);
}
