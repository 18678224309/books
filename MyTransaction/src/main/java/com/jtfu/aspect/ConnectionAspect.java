package com.jtfu.aspect;

import com.jtfu.connection.MyConnection;
import com.jtfu.transaction.TransactionMangage;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.sql.Connection;

@Component
@Aspect
public class ConnectionAspect {

    @Around("execution(* javax.sql.DataSource.getConnection(..))")
    public Connection around(ProceedingJoinPoint proceedingJoinPoint){
        try {
//            return (Connection) proceedingJoinPoint.proceed();
            return new MyConnection((Connection) proceedingJoinPoint.proceed(), TransactionMangage.getCurrentTransaction());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return  null;
    }
}