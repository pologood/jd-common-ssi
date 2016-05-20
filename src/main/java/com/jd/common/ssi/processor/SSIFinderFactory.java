package com.jd.common.ssi.processor;

import com.jd.common.ssi.factory.CommonFactory;
import com.jd.common.ssi.processor.impl.SSIFinderImpl;

/**
 * Created by caozhifei on 2016/5/20.
 */
public class SSIFinderFactory implements CommonFactory {
    private static SSIFinder ssiFinder = new SSIFinderImpl();
    @Override
    public Object getObject(String name) {
        if(ssiFinder == null){
            ssiFinder = new SSIFinderImpl();
        }
        return ssiFinder;
    }
}
