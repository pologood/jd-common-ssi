package com.jd.common.ssi.http;

import com.jd.common.ssi.processor.SSIFinderFactory;
import com.jd.common.ssi.processor.SSIProcessor;
import com.jd.common.ssi.processor.impl.SSIProcessorImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * Created by caozhifei on 2016/5/20.
 */
public class SSIPrintWriter extends PrintWriter {
    private SSIProcessor ssiProcessor;
    public SSIPrintWriter(Writer out) {
        super(out);
        ssiProcessor = new SSIProcessorImpl(new SSIFinderFactory());
    }
    @Override
    public void write(int c){
        try {
            ssiProcessor.process((char)c,super.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void flush() {
        try {
            ssiProcessor.flush(super.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
