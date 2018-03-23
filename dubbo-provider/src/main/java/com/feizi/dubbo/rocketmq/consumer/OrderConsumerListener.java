package com.feizi.dubbo.rocketmq.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.feizi.dubbo.rocketmq.topic.MqTopics;
import com.feizi.dubbo.utils.CommonUtils;
import com.touna.loan.common.service.rocketmq.ConsumeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 消费者(订单数据接收)监听器
 * Created by feizi on 2018/3/22.
 */
public class OrderConsumerListener implements ConsumeListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumerListener.class);

    @Override
    public boolean consume(Message message) {
        LOGGER.info("==========================OrderConsumerListener Consumer Start=====================");
        if(CommonUtils.isEmpty(message)){
            LOGGER.error("消费者接收到一条空的MQ消息...");
            //接收到空消息，也表明此次消费成功，返回true
            return true;
        }

        //获取topic值
        String topic = message.getTopic();

        /*判断topic是否正确*/
        if(!MqTopics.TOPIC_ORDER_CREATE.equals(topic)){
            //如果topic错误，比表明此次消费成功，返回true
            return true;
        }

        if(!(message instanceof MessageExt)){
            //判断消息类型是否MessageExt， 如果类型错误，也表明此次消费成功，返回true
            return true;
        }

        MessageExt messageExt = (MessageExt) message;
        //消息体
        byte[] msgBody = messageExt.getBody();
        if(null == msgBody || msgBody.length == 0){
            //判断消息体内容是否为空，如果为空，也表明此次消费成功，返回true
            return true;
        }

        //获取msgId值（用于追踪日志）
        String msgId = messageExt.getMsgId();
        //将消息体内容转换成String类型
        String mqMsg = CommonUtils.toString(msgBody);

        //打印消费者接收的MQ消息
        LOGGER.info("OrderConsumerListener received msg, message topic is: {}, msgId is: {}, message body is: {}", topic, msgId, mqMsg);
        if(messageExt.getReconsumeTimes() == 3){
            LOGGER.info("消息： {}, 已经重试了3次，停止重试", mqMsg);
        }

        boolean result = false;

        //将json字符串反序列化成JSONObject对象
        JSONObject jsonObject = JSON.parseObject(mqMsg);

        //根据topic实现相应的业务操作
        switch (topic){
            case MqTopics.TOPIC_ORDER_CREATE:
                /**
                 * TODO 处理业务逻辑
                 */
                //取出订单orderId
                String orderId = (String) jsonObject.get("orderId");
                //取出订单编号orderCode
                String orderCode = (String) jsonObject.get("orderCode");

                LOGGER.info("===订单orderId: {}, 订单编号orderCode: {}", orderId, orderCode);
                break;
            default:
                break;
        }

        LOGGER.info("==========================OrderConsumerListener Consumer End=====================");
        return result;
    }
}
