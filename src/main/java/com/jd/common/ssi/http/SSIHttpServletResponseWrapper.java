package com.jd.common.ssi.http;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * 对response进行封装，实现自己的resonse方法
 * Created by caozhifei on 2016/5/20.
 */
public class SSIHttpServletResponseWrapper extends HttpServletResponseWrapper {
    private  SSIServletOutputStream servletOutputStream;
    private PrintWriter writer;

    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @param response
     * @throws IllegalArgumentException if the response is null
     */
    public SSIHttpServletResponseWrapper(HttpServletResponse response) {
        super(response);
        try {
            String encoding = response.getCharacterEncoding();
            this.servletOutputStream = new SSIServletOutputStream(encoding,response.getOutputStream());
            OutputStreamWriter osw = new OutputStreamWriter(this.servletOutputStream, encoding);
            this.writer = new PrintWriter(osw);
            setCharacterEncoding(encoding);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //重载父类获取writer的方法,
    @Override
    public PrintWriter getWriter() throws IOException {
        return this.writer;
    }
    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return this.servletOutputStream;
    }
    //重载父类获取flushBuffer的方法
    @Override
    public void flushBuffer() throws IOException {
        if (this.writer != null) {
            this.writer.flush();
        }
        if(this.servletOutputStream != null){
            this.servletOutputStream.flush();
        }

    }

}
