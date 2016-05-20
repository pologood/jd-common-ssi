package com.jd.common.ssi;


import com.jd.common.ssi.http.SSIHttpServletResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Created by caozhifei on 2016/5/20.
 */
public class SSIFilter implements Filter {
    private String encoding = "UTF-8";
    private Set<String> excluded;
    private int maxPageSize = 1024 * 200;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest inRequest = (HttpServletRequest) request;
        HttpServletResponse inResponse = (HttpServletResponse) response;
        String encoding =inRequest.getCharacterEncoding();
        encoding = encoding == null ? this.encoding : encoding;
        String uri = inRequest.getRequestURI();
        // 判断是否在排除范围之内的请求
//        if (MatchUtil.simpleMatch(excluded, uri)) {
//            chain.doFilter(request, response);
//            return;
//        }
        SSIHttpServletResponseWrapper wrapper = new SSIHttpServletResponseWrapper(inResponse);
        wrapper.setCharacterEncoding(encoding);
        chain.doFilter(inRequest, wrapper);
//        if (isRefresh) {
//            inResponse.setDateHeader("Last-Modified", System.currentTimeMillis());
//            isRefresh = false;
//        }
        wrapper.setContentLength(-1);
        wrapper.flushBuffer();
    }

    @Override
    public void destroy() {

    }
}
