package com.abt.mqtt.event;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @描述： @CallbackEvent
 * @作者： @黄卫旗
 * @创建时间： @2018/9/21
 */
public class CallbackEvent {

    private int type;
    private MqttMessage msg;
    private String topic;
    private Throwable cause;

    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    private IMqttDeliveryToken token;

    public IMqttDeliveryToken getToken() {
        return token;
    }

    public void setToken(IMqttDeliveryToken token) {
        this.token = token;
    }

    public MqttMessage getMsg() {
        return msg;
    }

    public void setMsg(MqttMessage msg) {
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


}
