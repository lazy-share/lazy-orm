package com.lazy.orm.log;

import com.lazy.orm.log.log.Log;
import com.lazy.orm.log.log.LogFactory;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private Log log = LogFactory.getLog(AppTest.class);

    @Test
    public void test(){
        log.debug("debug");
        log.error("error");
        log.trace("trace");
        log.warn("warn");
        log.debug("debug");
    }
}
