package com.jd.common.ssi.processor.impl;

import com.jd.common.ssi.commond.SSICommand;
import com.jd.common.ssi.constant.CommandEnum;
import com.jd.common.ssi.factory.CommonFactory;
import com.jd.common.ssi.processor.SSIFinder;
import com.jd.common.ssi.processor.SSIProcessor;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

/**
 * Created by caozhifei on 2016/5/20.
 */
public class SSIProcessorImpl implements SSIProcessor {
    private CommonFactory factory;
    /**
     * SSI指令的最大长度
     */
    private int bufferMaxLength = 200;
    /**
     * SSI指令缓冲区
     */
    private StringBuilder buffer;

    private String encoding;

    public SSIProcessorImpl(String encoding,CommonFactory factory) {
        this.encoding = encoding;
        this.factory = factory;
        buffer = new StringBuilder(bufferMaxLength);
    }

    public SSIProcessorImpl(String encoding,CommonFactory factory, int commandMaxLength) {
        this.encoding = encoding;
        this.factory = factory;
        this.bufferMaxLength = commandMaxLength;
        buffer = new StringBuilder(this.bufferMaxLength);
    }

    @Override
    public void process(char c, Writer writer) throws IOException {
        //直接写入
        if (c != CommandEnum.COMMAND_PREFIX.getDesc().charAt(0) && buffer.length() <= 0) {
            writer.append(c);
            return;
        }

        //< 开始
        buffer.append(c);
        //无法对缓冲区指令进行判断，所以继续写入字符进行下次验证
        if (buffer.length() < CommandEnum.COMMAND_PREFIX.getDesc().length()) {
            return;
        }

        //凑够SSI指令前缀数量，判断是否为SSI指令前缀，如果不是直接输出，如果是继续往缓冲区写入字符
        boolean checkPrefixResult = checkPrefix(writer);
        if (checkPrefixResult) {
            return;
        }

        handleCommand(writer);

        //超过缓冲区大小，还没有找到完整的ssi指令，直接清空缓冲区，重新开始查找新的指令
        if (buffer.length() > this.bufferMaxLength) {
            writer.write(this.buffer.toString());
            buffer.setLength(0);
        }
    }

    /**
     * 判断缓冲区中是否包含SSI指令前缀,如果没有包含则直接清空缓冲区写入输入流
     *
     * @param writer
     * @return
     * @throws IOException
     */
    private boolean checkPrefix(Writer writer) throws IOException {
        if (this.buffer.length() >= CommandEnum.COMMAND_PREFIX.getDesc().length()) {
            int prefixIndex = buffer.indexOf(CommandEnum.COMMAND_PREFIX.getDesc());
            if (prefixIndex < 0) {
                //写入输出流并清空缓冲区
                writer.write(this.buffer.toString());
                buffer.setLength(0);
                return true;
            }
        }
        return false;
    }

    /**
     * 判断缓冲区中是否包含SSI指令前缀,如果没有包含则直接清空缓冲区写入输入流
     *
     * @param writer
     * @return
     * @throws IOException
     */
    private void handleCommand(Writer writer) throws IOException {
        if (this.buffer.length() > (CommandEnum.COMMAND_PREFIX.getDesc().length() + CommandEnum.COMMAND_SUFFIX.getDesc().length())) {
            int prefixLength = CommandEnum.COMMAND_PREFIX.getDesc().length();
            int suffixIndex = buffer.indexOf(CommandEnum.COMMAND_SUFFIX.getDesc());
            if (suffixIndex > prefixLength) {
                //写入输出流并清空缓冲区

                SSIFinder ssiFinder = (SSIFinder) factory.getObject(null);
                String commandStr = buffer.toString();
                SSICommand ssiCommand = ssiFinder.findCommand(commandStr);
                Map<String, String> map = ssiFinder.findPara(commandStr);
                writer.write(ssiCommand.execute(map));
                buffer.setLength(0);
            }
        }
    }


    /**
     * 清空缓冲区字符
     *
     * @param writer
     */
    @Override
    public void flush(Writer writer) throws IOException {
        writer.write(buffer.toString());
        buffer.setLength(0);
        writer.flush();
    }
}
