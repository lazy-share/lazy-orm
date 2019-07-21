package com.lazy.orm;

import com.lazy.orm.common.Log;
import com.lazy.orm.common.LogFactory;
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
