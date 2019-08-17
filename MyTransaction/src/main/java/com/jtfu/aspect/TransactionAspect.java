package com.jtfu.aspect;

import com.jtfu.annotation.JtfuTransacation;
import com.jtfu.transaction.Transaction;
import com.jtfu.transaction.TransactionMangage;
import com.jtfu.util.TransactionType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(10000)
public class TransactionAspect {

    @Around(value = "@annotation(com.jtfu.annotation.JtfuTransacation)")
    public Object transaAround(ProceedingJoinPoint joinPoint){
       MethodSignature methodSignature= (MethodSignature) joinPoint.getSignature();
       JtfuTransacation annotation= methodSignature.getMethod().getAnnotation(JtfuTransacation.class);
       String groupId=null;
        if(annotation.isStart()){
            groupId=TransactionMangage.createGroup();
       }else{
            groupId=TransactionMangage.getCurrent();
       }

        Transaction transaction=TransactionMangage.createTran(groupId);

        //执行本地逻辑
        try {
            //Spring 会帮我们执行mysql的事物  一直等待
            joinPoint.proceed();
            //提交本地事物状态  ---commit
            TransactionMangage.commitTransaction(transaction,annotation.isEnd(), TransactionType.COMMIT);
        } catch (Throwable throwable) {
            // 回滚
            TransactionMangage.commitTransaction(transaction,annotation.isEnd(), TransactionType.ROLLBACK);
            throwable.printStackTrace();

        }
        return null;
    }
}
