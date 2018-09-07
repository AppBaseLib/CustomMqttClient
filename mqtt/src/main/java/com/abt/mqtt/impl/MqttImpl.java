package com.abt.mqtt.impl;

import android.content.Context;

import com.abt.mqtt.base.BaseMqttService;
import com.abt.mqtt.base.SSLHandler;
import com.abt.mqtt.config.MqttConfig;
import com.abt.mqtt.config.SSLConfig;
import com.abt.mqtt.global.MqttConnectionStatus;

import javax.net.ssl.SSLSocketFactory;

/**
 * @author 黄卫旗
 * @description MqttImpl
 * @time 2018/09/05
 */
public class MqttImpl implements IMqtt {

    private static final String TAG = MqttImpl.class.getSimpleName();
    private BaseMqttService mMqttService;
    private MqttConnectionStatus mConnectionStatus = MqttConnectionStatus.NOTCONNECTED_DATADISABLED;

    private MqttImpl() {};

    private static class InnerClass {
        public static MqttImpl INSTANCE = new MqttImpl();
    }

    public static MqttImpl getInstance() {
        return InnerClass.INSTANCE;
    }

    public void init(Context context) {
        LogHelper.d(TAG, "init client");
        boolean success = buildMqttService(context);
        if (success) connect();
    }

    /**
     * 构建EasyMqttService对象
     */
    private boolean buildMqttService(Context context) {
        mConnectionStatus = MqttConnectionStatus.INITIAL;
        SSLSocketFactory sslSocketFactory = null;
        try {
            sslSocketFactory = SSLHandler.getSSLSocket(SSLConfig.CA_PATH,
                    SSLConfig.CRT_PATH, SSLConfig.KEY_PATH, SSLConfig.PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        mMqttService = new BaseMqttService.Builder()
                //设置自动重连
                .autoReconnect(MqttConfig.AUTO_RECONNECT)
                //设置不清除回话session 可收到服务器之前发出的推送消息
                .cleanSession(MqttConfig.CLEAN_SESSION)
                .retained(MqttConfig.RETAINED)
                //唯一标示 保证每个设备都唯一就可以 建议 imei
                .clientId(MqttConfig.CLIENT_ID)
                //mqtt服务器地址 格式例如：tcp://10.0.261.159:1883
                .serverUrl(MqttConfig.SERVER_URL)
                .userName(MqttConfig.USER_NAME)
                .passWord(MqttConfig.PASSWORD)
                .timeOut(MqttConfig.TIME_OUT)
                //心跳包默认的发送间隔
                .keepAliveInterval(MqttConfig.HEART_BEAT_INTERVAL)
                //构建出EasyMqttService 建议用application的context
                .sslSocketFactory(sslSocketFactory)
                .bulid(context);
        LogHelper.d(TAG, "SERVER_URL="+MqttConfig.SERVER_URL);
        return true;
    }

    /**
     * 连接Mqtt服务器
     */
    private void connect() {
        LogHelper.d(TAG, "client inited, start to connect server.");
        mConnectionStatus = MqttConnectionStatus.CONNECTING;
        mMqttService.connect();
    }

    /**
     * 订阅主题 这里订阅三个主题分别是"a", "b", "c"
     */
    public void subscribe(String[] topics, int[] qoss) {
        LogHelper.d(TAG, "subscribe, topics="+topics+", qoss="+qoss);
        // String[] topics = new String[]{"a", "b", "c"};
        // 主题对应的推送策略 分别是0, 1, 2
        // 建议服务端和客户端配置的主题一致
        // 0 表示只会发送一次推送消息 收到不收到都不关心
        // 1 保证能收到消息，但不一定只收到一条
        // 2 保证收到切只能收到一条消息
        // int[] qoss = new int[]{0, 1, 2};
        mMqttService.subscribe(topics, qoss);
    }

    /**
     * 关闭连接
     */
    @Override
    public void close() {
        LogHelper.d(TAG, "close");
        mMqttService.close();
    }

    /**
     * 断开连接
     */
    @Override
    public void disconnect() {
        LogHelper.d(TAG, "disconnect");
        mConnectionStatus = MqttConnectionStatus.NOTCONNECTED_USERDISCONNECT;
        mMqttService.disconnect();
    }

    /**
     * 判断服务是否连接
     */
    @Override
    public boolean isConnected() {
        if (mConnectionStatus == null) {
            return mMqttService.isConnected();
        }
        return (mConnectionStatus == MqttConnectionStatus.CONNECTED);
    }

    /**
     * 发布消息
     */
    @Override
    public void publish(String msg, String topic, int qos, boolean retained) {
        LogHelper.d(TAG, "publish, msg="+msg+", topic="+topic+", qos="+qos+", retained="+retained);
        mMqttService.publish(msg, topic, qos, retained);
    }

}
