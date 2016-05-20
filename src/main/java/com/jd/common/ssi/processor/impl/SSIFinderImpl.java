package com.jd.common.ssi.processor.impl;

import com.jd.common.ssi.commond.SSICommand;
import com.jd.common.ssi.commond.SSICommandFactory;
import com.jd.common.ssi.constant.CommandEnum;
import com.jd.common.ssi.factory.CommonFactory;
import com.jd.common.ssi.processor.SSIFinder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by caozhifei on 2016/5/19.
 */
public class SSIFinderImpl implements SSIFinder {

    private CommonFactory factory;

    public SSIFinderImpl(CommonFactory factory) {
        this.factory = factory;
    }

    public SSIFinderImpl() {
        factory = new SSICommandFactory();
    }

    @Override
    public SSICommand findCommand(String command) {
        command = clearPrefixAndSuffix(command);
        if (command == null || command == "") {
            return null;
        }
        command = command.substring(0, command.indexOf(" "));
        SSICommand ssiCommand = (SSICommand) factory.getObject(command);
        return ssiCommand;
    }

    @Override
    public Map<String, String> findPara(String command) {
        command = clearPrefixAndSuffix(command);
        if (command == null || command == "") {
            return null;
        }
        command = command.substring(command.indexOf(" ")+1, command.length());
        String[] paraValues = command.split(" ");
        Map<String, String> paraMap = new HashMap<String, String>();
        for (String paraValue : paraValues) {
            String[] paraValuePair = paraValue.split("=");
            paraMap.put(paraValuePair[0], paraValuePair[1]);
        }
        return paraMap;
    }

    /**
     * 清除指令前缀和后缀
     *
     * @param command
     * @return
     */
    private String clearPrefixAndSuffix(String command) {
        if (command == null) {
            return null;
        }
        if (command.contains(CommandEnum.COMMAND_PREFIX.getDesc())) {
            command = command.replace(CommandEnum.COMMAND_PREFIX.getDesc(), "");
        }
        if (command.contains(CommandEnum.COMMAND_SUFFIX.getDesc())) {
            command = command.replace(CommandEnum.COMMAND_SUFFIX.getDesc(), "");
        }
        return command.trim();
    }
}
