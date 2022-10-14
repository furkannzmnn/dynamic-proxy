package org.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class ProxyHandler implements InvocationHandler {

    private List<?> arguments;

    private final Logger logger = Logger.getLogger(ProxyHandler.class.getName());

    private final Object target;

    public ProxyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            transactionStart();
            final Object invoke = method.invoke(target, args);
            commit();
            return invoke;
        }catch (Exception e){
            rollback();
            throw e;
        }
    }

    private void rollback() {
        logger.info("Rollback");
    }

    private void commit() {
        flush();
        logger.info("committed");
    }

    private void transactionStart(Object... args) {
        arguments = List.of(args);
        logger.info("transaction started");
    }

    public void flush() {
        logger.info("flushed persistence context");
        IntStream.range(0, arguments.size()).forEach(i -> arguments.remove(i));
    }
}
