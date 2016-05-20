package com.jd.common.ssi.processor;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * Created by caozhifei on 2016/5/20.
 */
public interface SSIProcessor {

    /**
     * 写入字符并进行ssi指令处理
     * @param c
     * @param writer
     * @throws IOException
     */
    void process(char c,Writer writer) throws IOException;

    /**
     * 清空缓冲区
     * @param writer
     */
    void flush(Writer writer) throws IOException;
}
