package com.bruce.staging.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.*;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
public class TransactionUtils {

    /**
     * transactionTemplate
     */
    private static TransactionTemplate transactionTemplate = SpringContextUtils.getBean(TransactionTemplate.class);

    /**
     * 在事务中执行，返回结果
     *
     * @param supplier
     * @return T
     */
    public static <T> T doTransaction(Supplier<T> supplier) {
        // 如果有事务，指直接执行
        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            return supplier.get();
        }
        // 如果没有事务，新建一个事务执行
        return transactionTemplate.execute(status -> supplier.get());

    }

    /**
     * 在事务中执行，返回结果
     *
     * @param function
     * @param t
     * @return R
     */
    public static <T, R> R doTransaction(Function<T, R> function, T t) {
        // 如果有事务，指直接执行
        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            return function.apply(t);
        }
        // 如果没有事务，新建一个事务执行
        return transactionTemplate.execute(status -> function.apply(t));
    }

    /**
     * 在事务中执行，不返回结果
     *
     * @param consumer
     * @param t
     */
    public static <T> void doTransactionWithoutResult(Consumer<T> consumer, T t) {
        // 如果有事务，指直接执行
        if (TransactionSynchronizationManager.isSynchronizationActive()) {
            consumer.accept(t);
        }
        // 如果没有事务，新建一个事务执行
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                // 需要事务控制的业务代码
                consumer.accept(t);
            }
        });
    }

    /**
     * 在事务后执行
     *
     * @param consumer
     * @param t
     */
    public static <T> void doAfterTransaction(Consumer<T> consumer, T t) {
        // 如果没有事务，指直接执行
        if (!TransactionSynchronizationManager.isSynchronizationActive()) {
            consumer.accept(t);
        }
        // 如果存在事务，在事务后执行
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCompletion(int status) {
                if (status == TransactionSynchronization.STATUS_COMMITTED) {
                    consumer.accept(t);
                }
            }
        });
    }
}
