package com.jd.common.ssi.http;

import com.jd.common.ssi.processor.SSIFinderFactory;
import com.jd.common.ssi.processor.SSIProcessor;
import com.jd.common.ssi.processor.impl.SSIProcessorImpl;

import javax.servlet.ServletOutputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 自定义输出流
 * Created by caozhifei on 2016/5/20.
 */
public class SSIServletOutputStream extends ServletOutputStream {
    private int initialSize;
    private Writer writer;
    private SSIProcessor ssiProcessor;

    public SSIServletOutputStream(int initialSize) {
        this.initialSize = initialSize;
        writer = new CharArrayWriter(initialSize);
        ssiProcessor = new SSIProcessorImpl(new SSIFinderFactory());
    }

    public SSIServletOutputStream(int initialSize, SSIProcessor ssiProcessor) {
        this.initialSize = initialSize;
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
}
