package com.feizi.dubbo.rocketmq.producer;

import com.alibaba.fastjson.JSON;
import com.alibaba.rocketmq.common.message.Message;
import com.feizi.dubbo.rocketmq.producer.message.EventType;
import com.feizi.dubbo.rocketmq.producer.message.MessageBody;
import com.feizi.dubbo.rocketmq.producer.message.MsgType;
import com.feizi.dubbo.rocketmq.topic.MqTopics;
import com.feizi.dubbo.utils.CommonUtils;
import com.touna.loan.common.service.rocketmq.MQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * MQ消息生产者
 * Created by feizi on 2018/3/22.
 */
@Component
public class OrderServiceProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceProducer.class);

    @Resource
    private MQProducer producer;

    /**
     * 发送订单消息
     * @param orderId 订单ID
     * @param orderCode 订单编号
     */
    public void sendOrderMessage(String orderId, String orderCode){
        MessageBody<Map<String, Object>> messageBody = new MessageBody<>();
        messageBody.setRequestId(UUID.randomUUID().toString());
        messageBody.setUuid(UUID.randomUUID().toString());
        messageBody.setTimeStamp(new Date().getTime());
        /*订单接收事件类型*/
        messageBody.setEventType(EventType.EVENT_TYPE_ORDER_RECEIVE.getEvent());
        /*订单模块消息类型*/
        messageBody.setMsgType(MsgType.MSG_TYPE_ORDER.getMsgType());

        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        map.put("orderCode", orderCode);

        /*消息体内容：订单ID*/
        messageBody.setData(map);
        LOGGER.info("==================开始发送MQ消息，MQ消息topic：{}, MQ消息内容data：{}", MqTopics.TOPIC_ORDER_CREATE, JSON.toJSONString(messageBody));

        /*封装MQ消息*/
        Message message = wrapMqMessage(MqTopics.TOPIC_ORDER_CREATE, messageBody);
        /*发送MQ消息, 如果失败，则尝试发送3次*/
        boolean result = producer.sendMessge(message, 3);

        LOGGER.info("==================发送MQ消息结束，MQ消息发送结果result：{}, 调用追踪唯一requestId：{}", result, messageBody.getRequestId());
    }

    /**
     * 封装MQ可以接收的消息类型
     * @param topic 消息Topic
     * @param msgBody 消息体内容
     * @return
     */
    private Message wrapMqMessage(String topic, MessageBody<?> msgBody){
        return new Message(topic, CommonUtils.toBinary(JSON.toJSONString(msgBody)));
    }
}
