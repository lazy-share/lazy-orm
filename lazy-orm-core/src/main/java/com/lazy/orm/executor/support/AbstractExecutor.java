package com.lazy.orm.executor.support;

import com.lazy.orm.executor.Executor;
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


}
