package com.jtfu.transaction;

import com.alibaba.fastjson.JSONObject;
import com.jtfu.netty.NettyClient;
import com.jtfu.util.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class TransactionMangage {
    private  static ThreadLocal<String>  current = new ThreadLocal<>();
    private  static ThreadLocal<Transaction> currentTransaction = new ThreadLocal<>();
    private  static final   Map<String, Map<String,Transaction>> GROUP_MAP = new HashMap<>();

    private  static NettyClient nettyClient;

    @Autowired
    public void setNettyClient(NettyClient nettyClient){
        this.nettyClient=nettyClient;
    }
    //创建事务组
    public static String createGroup(){
        String id= UUID.randomUUID().toString();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put(TransactionType.GROUPID,id);
        jsonObject.put(TransactionType.COMMAND,TransactionType.CREATE);
        current.set(id);
        GROUP_MAP.put(id,new HashMap<>());
        nettyClient.send(jsonObject);
        return id;
    }

    //创建事务
    public static Transaction createTran(String groupId){
        String id=UUID.randomUUID().toString();
        Transaction transaction=new Transaction(id,groupId);
        currentTransaction.set(transaction);
        if(GROUP_MAP.get(groupId)==null){
            GROUP_MAP.put(groupId,new HashMap<>());
        }
        GROUP_MAP.get(groupId).put(id,transaction);
        return transaction;
    }

    //提交本地事物
    public static void commitTransaction(Transaction transaction,boolean isEnd,String transactionType){
        JSONObject jsonObject  = new JSONObject();
        jsonObject.put(TransactionType.GROUPID,transaction.getGroupId());
        jsonObject.put(TransactionType.TRANSACTIONID,transaction.getTransactionId());
        jsonObject.put(TransactionType.TRANSACTIONtype,transactionType);
        jsonObject.put(TransactionType.COMMAND,TransactionType.ADD);
        jsonObject.put(TransactionType.ISEND,isEnd);
        nettyClient.send(jsonObject);
        System.out.println("执行了添加事务");
    }

    public static Transaction getTransactionById(String groupId,String id){
        return GROUP_MAP.get(groupId).get(id);
    }

    public static Transaction getCurrentTransaction(){
        return  currentTransaction.get();
    }


    public static String getCurrent() {
        return current.get();
    }

    public static void setCurrent(String groupId) {
        current.set(groupId);
    }

}
