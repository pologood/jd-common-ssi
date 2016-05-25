package com.jd.common.ssi.http;

import com.jd.common.ssi.processor.SSIFinderFactory;
import com.jd.common.ssi.processor.SSIProcessor;
import com.jd.common.ssi.processor.impl.SSIProcessorImpl;

import javax.servlet.ServletOutputStream;
import java.io.*;

/**
 * 自定义输出流
 * Created by caozhifei on 2016/5/20.
 */
public class SSIServletOutputStream extends ServletOutputStream {
    private OutputStreamWriter writer;
    private SSIProcessor ssiProcessor;

    public SSIServletOutputStream(String encoding,ServletOutputStream outputStream) {
        try {
            writer = new OutputStreamWriter(outputStream,encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ssiProcessor = new SSIProcessorImpl(encoding,new SSIFinderFactory());
    }

    public SSIServletOutputStream(String encoding, SSIProcessor ssiProcessor,ServletOutputStream outputStream) {
        try {
            writer = new OutputStreamWriter(outputStream,encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.ssiProcessor = ssiProcessor;
    }

    /**
     * 重写写入字符方法，查找ssi指令并处理
     *
     * @param b
     * @throws IOException
     */
    @Override
    public void write(int b) throws IOException {
        ssiProcessor.process((char) b, writer);
    }

    @Override
    public void flush() throws IOException {
        ssiProcessor.flush(writer);
    }
    public OutputStreamWriter getWriter(){
        return this.writer;
    }
}
