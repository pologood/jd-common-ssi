package com.jd.common.ssi.factory;

/**
 * 通过对象工厂
 * Created by caozhifei on 2016/5/19.
 */
public interface CommonFactory {
    /**
     * 通过name获取对象
     *
     * @param name
     * @return
     */
    Object getObject(String name);
}
