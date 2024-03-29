package com.jtfu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.*;

/**
 * 作为事务管理者，它需要：
 * 1. 创建并保存事务组
 * 2. 保存各个子事务在对应的事务组内
 * 3. 统计并判断事务组内的各个子事务状态，以算出当前事务组的状态（提交or回滚）
 * 4. 通知各个子事务提交或回滚
 */

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    // 事务组中的事务状态列表

    private static Map<String, List<String>> transactionTypeMap = new HashMap<String, List<String>>();

    // 返回事务组数据
    private static Map<String, LinkedList<JSONObject>> txGroupResult=new HashMap<String, LinkedList<JSONObject>>();
    // 事务组是否已经接收到结束的标记
    private static Map<String, Boolean> isEndMap = new HashMap<String, Boolean>();

//    // 事务组中应该有的事务个数
//    private static Map<String, Integer> transactionCountMap = new HashMap<String, Integer>();

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.add(ctx.channel());
    }

    /**
     * 创建事务组，并且添加保存事务
     * 并且需要判断，如果所有事务都已经执行了（有结果了，要么回滚，要么提交），且其中有一个事务需要回滚，那么通知所有客户端进行回滚
     * 否则，则通知所有客户端进行提交
     * @param ctx
     * @param msg
     * @throws Exception
     */


    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("接受数据:" + msg.toString());

        JSONObject jsonObject = JSON.parseObject((String) msg);

        String command = jsonObject.getString(TransactionType.COMMAND); // create-创建事务组，add-添加事务
        String groupId = jsonObject.getString(TransactionType.GROUPID);   // 事务组id
        String transactionType = jsonObject.getString(TransactionType.TRANSACTIONtype); // 子事务类型，commit-待提交，rollback-待回滚
        Integer transactionCount = jsonObject.getInteger("transactionCount");   // 事务数量
        Boolean isEnd = jsonObject.getBoolean(TransactionType.ISEND); // 是否是结束事务
        String transactionId = jsonObject.getString(TransactionType.TRANSACTIONID);//事物ID


        if (TransactionType.CREATE.equals(command)) {
            // 创建事务组
            transactionTypeMap.put(groupId, new ArrayList<String>());
        } else if (TransactionType.ADD.equals(command)) {
            // 加入事务组
            transactionTypeMap.get(groupId).add(transactionType);

            if (isEnd) {
                isEndMap.put(groupId, true);
//                transactionCountMap.put(groupId, transactionCount);
            }

            JSONObject result = new JSONObject();
            result.put(TransactionType.GROUPID, groupId);
            result.put(TransactionType.TRANSACTIONID,transactionId);
            // 如果已经接收到结束事务的标记,则看是否需要回滚
            if (isEndMap.get(groupId)!=null&&isEndMap.get(groupId)) {
                //commit
                //commit
                //commit
                if (transactionTypeMap.get(groupId).contains(TransactionType.ROLLBACK)){
                    result.put(TransactionType.COMMAND, TransactionType.ROLLBACK);
                    sendResult(result);
                } else {
                    result.put(TransactionType.COMMAND, TransactionType.COMMIT);
                    sendResult(result);
                }
            }

        }
    }

    private void sendResult(JSONObject result) {
        for (Channel channel : channelGroup) {
            System.out.println("发送数据:" + result.toJSONString());
            channel.writeAndFlush(result.toJSONString());
        }
    }
}
