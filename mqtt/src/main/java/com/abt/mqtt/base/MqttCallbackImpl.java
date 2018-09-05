package com.abt.mqtt.base;

import com.abt.basic.logger.LogHelper;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.greenrobot.eventbus.EventBus;

/**
 * @author 黄卫旗
 * @description MQTT监听并且接受消息
 * @time 2018/09/05
 */
public class MqttCallbackImpl implements MqttCallback {

    private static final String TAG = MqttCallbackImpl.class.getSimpleName();

    /**
     * 推送消息到达
     * @param topic
     * @param message
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        String msgContent = new String(message.getPayload());
        String detailLog = topic + ";qos:" + message.getQos() + ";retained:" + message.isRetained();
        LogHelper.i(TAG, "messageArrived:" + msgContent);
        LogHelper.i(TAG, detailLog);
        EventBus.getDefault().post(message);
    }

    /**
     * 传送完成
     * @param token
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        LogHelper.i(TAG, "deliveryComplete");
    }

    /**
     * 连接断开
     * @param cause
     */
    @Override
    public void connectionLost(Throwable cause) {
        LogHelper.i(TAG, "connectionLost");
        // 失去连接，重连
    }
}
