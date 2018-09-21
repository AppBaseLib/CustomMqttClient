package com.abt.mqtt.event;

import org.eclipse.paho.client.mqttv3.IMqttToken;

/**
 * @描述： @ConnectEvent
 * @作者： @黄卫旗
 * @创建时间： @2018/9/21
 */
public class ConnectEvent {

    private boolean status;
    private IMqttToken token;

    public void setToken(IMqttToken token) {
        this.token = token;
    }

    public IMqttToken getToken() {
        return token;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
