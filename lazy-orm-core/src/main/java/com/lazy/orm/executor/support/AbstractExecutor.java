package com.lazy.orm.executor.support;

import com.lazy.orm.common.Log;
import com.lazy.orm.common.LogFactory;
import com.lazy.orm.executor.Executor;
import com.lazy.orm.handler.ParameterValHandler;
import com.lazy.orm.handler.ResultHandler;
import com.lazy.orm.handler.SelectResultHandler;
import com.lazy.orm.handler.UpdateResultHandler;
import com.lazy.orm.handler.simple.SimpleParameterValHandler;
import com.lazy.orm.parser.SqlSourceParser;
import com.lazy.orm.parser.annotation.AnnotationSqlSourceParser;
import com.lazy.orm.tx.Transaction;

/**
 * <p>
 * 抽象执行器
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/20.
 */
public abstract class AbstractExecutor implements Executor {

    protected Transaction tx;
    protected ParameterValHandler parameterValHandler = new SimpleParameterValHandler();
    protected ResultHandler selectHandler = new SelectResultHandler();
    protected ResultHandler updateHandler = new UpdateResultHandler();
    protected SqlSourceParser sqlSourceParser = new AnnotationSqlSourceParser();
    protected Log log = LogFactory.getLog(getClass());


}
