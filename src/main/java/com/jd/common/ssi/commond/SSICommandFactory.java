package com.jd.common.ssi.commond;

import com.jd.common.ssi.factory.CommonFactory;
import com.jd.common.ssi.commond.impl.SSIContainCommand;

import java.util.HashMap;

/**
 * Created by caozhifei on 2016/5/19.
 */
public class SSICommandFactory implements CommonFactory {
    /**
     * 指令处理类对象池
     */
    private static HashMap<String, SSICommand> commandMap = new HashMap<String, SSICommand>();

    //初始化指令处理类
    static {
        commandMap.put(SSIContainCommand.NAME, new SSIContainCommand());
    }


    @Override
    public Object getObject(String name) {
        return commandMap.get(name);
    }
}
