package com.feizi.dubbo.rocketmq.producer.message;

import java.io.Serializable;

/**
 * MQ消息体封装
 * Created by feizi on 2018/3/22.
 */
public class MessageBody<T> implements Serializable{
    private static final long serialVersionUID = 8210523012684522817L;
    /*调用追踪唯一ID识别号*/
    private String requestId;
    /*uuid消费幂等控制*/
    private String uuid;
    /*事件类型（例如是订单接收操作，或者是订单创建操作）*/
    private String eventType;
    /*消息类型（例如是订单模块消息，还是进件模块消息）*/
    private String msgType;
    /*消息产生时间戳*/
    private long timeStamp;
    /*消息内容*/
    private T data;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
