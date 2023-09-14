package com.bruce.staging.common.interceptor;

import com.baomidou.mybatisplus.extension.plugins.inner.DataChangeRecorderInnerInterceptor;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.update.Update;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;

import java.sql.Connection;

/**
 * 数据变更拦截器
 */
public class DataChangeInterceptor extends DataChangeRecorderInnerInterceptor {
    @Override
    public void beforePrepare(StatementHandler sh, Connection connection, Integer transactionTimeout) {
        super.beforePrepare(sh, connection, transactionTimeout);
    }

    @Override
    public OperationResult processInsert(Insert insertStmt, BoundSql boundSql) {
        return super.processInsert(insertStmt, boundSql);
    }

    @Override
    public OperationResult processUpdate(Update updateStmt, MappedStatement mappedStatement, BoundSql boundSql, Connection connection) {
        return super.processUpdate(updateStmt, mappedStatement, boundSql, connection);
    }

    @Override
    public OperationResult processDelete(Delete deleteStmt, MappedStatement mappedStatement, BoundSql boundSql, Connection connection) {
        return super.processDelete(deleteStmt, mappedStatement, boundSql, connection);
    }
}
