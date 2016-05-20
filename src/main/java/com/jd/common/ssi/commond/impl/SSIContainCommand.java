package com.jd.common.ssi.commond.impl;

import com.jd.common.ssi.commond.SSICommand;
import com.jd.common.ssi.constant.SSIConfig;

import java.util.Map;

/**
 * Created by caozhifei on 2016/5/19.
 */
public class SSIContainCommand implements SSICommand {
    public final static String NAME = "contain";
    /**
     * 该指令的有效参数数组
     */
    private String[] validParas = new String[]{"absolute", "encoding", "showError"};

    @Override
    public String execute(Map<String, String> paraMap) {
        String message = "";
        if (paraMap == null) {
            message = "[command Parameter is null; check the commond Parameter!]";
            return message;
        }
        String absoluteValue = paraMap.get(validParas[0]);
        String encoding = paraMap.get(validParas[1]) == null ? SSIConfig.getDefaultEncoding() : paraMap.get(validParas[1]);
        String showError = paraMap.get(validParas[2]) == null ? "true" : paraMap.get(validParas[2]);

        if(absoluteValue == null){
            if("true".equalsIgnoreCase(showError)){
                message = "[absolute Parameter is a must,check the absolute]";
            }
            return message;
        }
        message ="contain接口待实现111";//TODO;
        return message;
    }
}
