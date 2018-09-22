package com.abt.mqtt.impl;

import android.content.Context;

/**
 * @author 黄卫旗
 * @description IMqtt
 * @time 2018/09/05
 */
public interface IMqtt {
    /**
     * 初始化
     */
    void init(Context context);
    /**
     * 订阅主题
     */
    void subscribe(String[] topics, int[] qoss);
    /**
     * 关闭连接
     */
    void close();
    /**
     * 断开连接
     */
    void disconnect();
    /**
     * 判断服务是否连接
     */
    boolean isConnected();
    /**
     * 发布消息
     */
    void publish(String jsonMsg, String topic, int qos, boolean retained);

}
