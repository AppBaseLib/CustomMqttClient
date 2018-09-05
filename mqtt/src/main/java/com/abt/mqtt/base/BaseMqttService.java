package com.abt.mqtt.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.abt.basic.logger.LogHelper;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

import javax.net.ssl.SSLSocketFactory;

/**
 * @author 黄卫旗
 * @description BaseMqttService
 * @time 2018/09/05
 */
public class BaseMqttService {

    private final String TAG = BaseMqttService.class.getSimpleName();
    private boolean canDoConnect = true;

    private MqttAndroidClient client;
    private MqttConnectOptions conOpt;
    private MqttCallback mqttCallback;
    private IMqttActionListener mqttActionListener;

    private Context context;
    private String serverUrl;
    private String userName;
    private String passWord;
    private String clientId;
    private int timeOut;
    private int keepAliveInterval;
    private boolean retained;
    private boolean cleanSession;
    private boolean autoReconnect;
    private SSLSocketFactory sslSocketFactory;
    /**
     * builder设计模式
     * @param builder
     */
    private BaseMqttService(Builder builder) {
        this.context = builder.context;
        this.serverUrl = builder.serverUrl;
        this.userName = builder.userName;
        this.passWord = builder.passWord;
        this.clientId = builder.clientId;
        this.timeOut = builder.timeOut;
        this.keepAliveInterval = builder.keepAliveInterval;
        this.retained = builder.retained;
        this.cleanSession = builder.cleanSession;
        this.autoReconnect = builder.autoReconnect;
        this.sslSocketFactory = builder.sslSocketFactory;
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() throws Exception {
        mqttCallback = new MqttCallbackImpl();
        mqttActionListener = new MqttActionListenerImpl();
        // 服务器地址（协议+地址+端口号）
        client = new MqttAndroidClient(context, serverUrl, clientId);
        // 设置MQTT监听并且接受消息
        client.setCallback(mqttCallback);
        conOpt = new MqttConnectOptions();
        // 清除缓存
        conOpt.setCleanSession(cleanSession);
        // 设置超时时间，单位：秒
        conOpt.setConnectionTimeout(timeOut);
        // 心跳包发送间隔，单位：秒
        conOpt.setKeepAliveInterval(keepAliveInterval);
        // 用户名
        conOpt.setUserName(userName);
        // 密码
        conOpt.setPassword(passWord.toCharArray());
        conOpt.setAutomaticReconnect(autoReconnect);
        // SSL支持
        conOpt.setSocketFactory(sslSocketFactory);
    }

    /**
     * Builder 构造类
     */
    public static final class Builder {
        private Context context;
        private String serverUrl;
        private String userName;
        private String passWord;
        private String clientId;
        private int timeOut;
        private int keepAliveInterval;
        private boolean retained;
        private boolean cleanSession;
        private boolean autoReconnect;
        private SSLSocketFactory sslSocketFactory;

        public Builder serverUrl(String serverUrl) {
            this.serverUrl = serverUrl;
            return this;
        }

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder passWord(String passWord) {
            this.passWord = passWord;
            return this;
        }

        public Builder clientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder timeOut(int timeOut) {
            this.timeOut = timeOut;
            return this;
        }

        public Builder keepAliveInterval(int keepAliveInterval) {
            this.keepAliveInterval = keepAliveInterval;
            return this;
        }

        public Builder retained(boolean retained) {
            this.retained = retained;
            return this;
        }

        public Builder autoReconnect(boolean autoReconnect) {
            this.autoReconnect = autoReconnect;
            return this;
        }

        public Builder cleanSession(boolean cleanSession) {
            this.cleanSession = cleanSession;
            return this;
        }

        public Builder sslSocketFactory(SSLSocketFactory sslSocketFactory) {
            this.sslSocketFactory = sslSocketFactory;
            return this;
        }

        public BaseMqttService bulid(Context context) {
            this.context = context;
            return new BaseMqttService(this);
        }
    }

    /**
     * 发布消息
     * @param msg
     * @param topic
     * @param qos
     * @param retained
     */
    public void publish(String msg, String topic, int qos, boolean retained) {
        try {
            client.publish(topic, msg.getBytes(), qos, retained);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭客户端
     */
    public void close() {
        try {
            client.close();
        } catch (Exception e) {
            LogHelper.e(TAG, e.toString());
        }
    }

    /**
     * 连接MQTT服务器
     */
    public void connect() {
        if (canDoConnect && !client.isConnected()) {
            try {
                client.connect(conOpt, null, mqttActionListener);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 订阅主题
     * @param topics 主题
     * @param qos    策略
     */
    public void subscribe(String[] topics, int[] qos) {
        try {
            // 订阅topic话题
            LogHelper.i(TAG, "execute subscribe -- qos = " + qos.toString());
            client.subscribe(topics, qos);
        } catch (Exception e) {
            LogHelper.e(TAG, e.toString());
        }
    }

    /**
     * 断开连接
     */
    public void disconnect() {
        try {
            client.disconnect();
        } catch (Exception e) {
            LogHelper.e(TAG, e.toString());
        }
    }

    /**
     * 判断连接是否断开
     */
    public boolean isConnected() {
        try {
            return client.isConnected();
        } catch (Exception e) {
            LogHelper.e(TAG, e.toString());
        }
        return false;
    }

    /**
     * 判断网络是否连接
     */
    private boolean isConnectIsNomarl() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            String name = info.getTypeName();
            LogHelper.i(TAG, "MQTT current network name：" + name);
            return true;
        } else {
            LogHelper.i(TAG, "MQTT no network");
            return false;
        }
    }
}
