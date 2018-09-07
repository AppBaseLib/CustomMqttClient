package com.abt.mqtt.config;

/**
 * @author 黄卫旗
 * @description MqttConfig
 * @time 2018/09/05
 */
public class MqttConfig {

    public static final String USER_NAME = "admin";
    public static final String PASSWORD = "password";

    public static final String CLIENT_ID = "CLIENT_ID"; // TODO
    //public static final String SERVER_URL = "tcp://10.0.261.159:1883"; // 无SSL的URL
    public static final String SERVER_URL = "ssl://commserver.qalink.cn:31638";
    public static final int HEART_BEAT_INTERVAL = 20; // 20秒
    public static final int TIME_OUT = 10; // 10秒

    public static final boolean AUTO_RECONNECT = true;
    public static final boolean RETAINED = false;
    public static final boolean CLEAN_SESSION = false;
}

