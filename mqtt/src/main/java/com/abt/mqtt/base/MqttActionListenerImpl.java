package com.abt.mqtt.base;

import android.util.Log;
import com.abt.basic.logger.LogHelper;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

/**
 * @author 黄卫旗
 * @description MQTT是否连接成功
 * @time 2018/09/05
 */
public class MqttActionListenerImpl implements IMqttActionListener {

    private static final String TAG = MqttActionListenerImpl.class.getSimpleName();

    /**
     * 连接成功
     */
    @Override
    public void onSuccess(IMqttToken token) {
        Log.i(TAG, "mqtt connect success ");
    }

    /**
     * 连接失败
     * @param token
     * @param throwable
     */
    @Override
    public void onFailure(IMqttToken token, Throwable throwable) {
        LogHelper.i(TAG, "mqtt connect failed ");
    }
}
