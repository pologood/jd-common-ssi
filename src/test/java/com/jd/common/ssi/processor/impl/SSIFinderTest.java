package com.jd.common.ssi.processor.impl;

import com.jd.common.ssi.commond.SSICommand;
import com.jd.common.ssi.processor.SSIFinder;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class SSIFinderTest {
    private SSIFinder finder = new SSIFinderImpl();
    private String command = "<!--#contain absolute=\"/export/Data/try.jd.local/common/config1/try_Header-new_utf8.html\" encoding=\"GBK\" showError=false -->";

    @Test
    public void testFindCommand() throws Exception {
        SSICommand ssiCommand = finder.findCommand(command);
        Map<String,String> map = finder.findPara(command);
        assertNotNull(ssiCommand);
        System.out.println(ssiCommand.execute(map));
    }

    @Test
    public void testFindPara() throws Exception {
        Map<String,String> map = finder.findPara(command);
        assertNotNull(map);
        System.out.println(map);

    }
}