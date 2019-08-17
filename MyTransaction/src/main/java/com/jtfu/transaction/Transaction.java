package com.jtfu.transaction;

import com.jtfu.util.Task;
import com.jtfu.util.TransactionType;

public class Transaction {
    //事务id
    private String transactionId;

    //事务组id
    private String groupId;

    //事务的状态
    private String transactionType;

    //阻塞任务
    private Task task;

    public Transaction(String transactionId,String groupId){
        this.transactionId=transactionId;
        this.groupId=groupId;
        this.task=new Task();
    }


    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
