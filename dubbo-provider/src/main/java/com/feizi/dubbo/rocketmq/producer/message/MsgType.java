package com.feizi.dubbo.rocketmq.producer.message;

/**
 * Created by feizi on 2018/3/22.
 */
public enum MsgType {
    MSG_TYPE_ORDER("MSG_TYPE_ORDER", "订单模块消息类型");

    /*消息类型（消息类型（例如是订单模块消息，还是进件模块消息））*/
    private String msgType;
    /*消息类型描述*/
    private String desc;

    MsgType(String msgType, String desc) {
        this.msgType = msgType;
        this.desc = desc;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getMsgType() {
        return msgType;
    }

    public String getDesc() {
        return desc;
    }
}
