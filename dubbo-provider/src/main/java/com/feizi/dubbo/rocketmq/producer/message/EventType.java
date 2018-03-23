package com.feizi.dubbo.rocketmq.producer.message;

/**
 * Created by feizi on 2018/3/22.
 */
public enum EventType {
    EVENT_TYPE_ORDER_RECEIVE("EVENT_TYPE_ORDER_RECEIVE", "订单接收操作类型");

    /*事件类型（例如是订单接收操作，或者是订单创建操作）*/
    private String event;
    /*事件类型描述*/
    private String desc;

    EventType(String event, String desc) {
        this.event = event;
        this.desc = desc;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
