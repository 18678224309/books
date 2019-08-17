package com.jtfu.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jtfu.transaction.Transaction;
import com.jtfu.transaction.TransactionMangage;
import com.jtfu.util.TransactionType;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    private ChannelHandlerContext context;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        context = ctx;
    }

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("接受数据:" + msg.toString());
        JSONObject jsonObject = JSON.parseObject((String) msg);

        String groupId = jsonObject.getString(TransactionType.GROUPID);
        String command = jsonObject.getString(TransactionType.COMMAND);
        String transactionId = jsonObject.getString(TransactionType.TRANSACTIONID);
        //拿到通知的事物对象
        Transaction transaction = TransactionMangage.getTransactionById(groupId, transactionId);
        System.out.println("接收command:" + command);
        if (transaction!=null){
            if (command.equals(TransactionType.ROLLBACK)){
                transaction.setTransactionType(TransactionType.ROLLBACK);
            }else{
                transaction.setTransactionType(TransactionType.COMMIT);
            }
            transaction.getTask().signalTask();
        }
        // 对事务进行操作
        //给指定事物放行
    }

    public synchronized Object call(JSONObject data) throws Exception {
        context.writeAndFlush(data.toJSONString()).channel().newPromise();
        return null;
    }
}
