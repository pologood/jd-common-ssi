package com.jd.common.ssi.processor.impl;

import com.jd.common.ssi.factory.CommonFactory;
import com.jd.common.ssi.processor.SSIFinderFactory;
import com.jd.common.ssi.processor.SSIProcessor;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class SSIProcessorImplTest {

    @Test
    public void testProcess() throws Exception {
        CommonFactory factory = new SSIFinderFactory();
        SSIProcessor processor = new SSIProcessorImpl(factory);
        PrintWriter writer = new PrintWriter("d:/logs/ssi.log");
        String content = "adftrhfhfghd456<!--#contain absolute=\"/export/Data/try.jd.local/common/config1/try_Header-new_utf8.html\" encoding=\"GBK\" showError=false -->dsfadsfsd4gdgwo哦多";
        for(char c : content.toCharArray()){
            processor.process(c,writer);
        }
        processor.flush(writer);
        writer.flush();
        System.out.println(writer);
    }

    @Test
    public void testProcess2() throws Exception {
        CommonFactory factory = new SSIFinderFactory();
        SSIProcessor processor = new SSIProcessorImpl(factory,10);
        PrintWriter writer = new PrintWriter("d:/logs/ssi.log");
        String content = "adftrhfhfghd456<!--#contain absolute=\"/export/Data/try.jd.local/common/config1/try_Header-new_utf8.html\" encoding=\"GBK\" showError=false -->dsfadsfsd4gdgwo哦多";
        for(char c : content.toCharArray()){
            processor.process(c,writer);
        }
        processor.flush(writer);
        writer.flush();
        System.out.println(writer);
    }
}