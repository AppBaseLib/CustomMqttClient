package com.abt.mqtt.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.abt.basic.BasicApplication;
import com.abt.basic.logger.LogHelper;
import com.abt.mqtt.impl.IMqtt;
import com.abt.mqtt.impl.MqttImpl;


/**
 * @author 黄卫旗
 * @description MqttService
 * @time 2018/09/05
 */
public class MqttService extends Service implements IMqtt {

    private static final String TAG = MqttService.class.getSimpleName();
    private MyBinder binder = new MyBinder();
    private IMqtt iMqtt;

    public class MyBinder extends Binder {
        public MqttService getService() {
            return MqttService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init(BasicApplication.getAppContext());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogHelper.d(TAG, "onStartCommand");
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogHelper.d(TAG, "onBind");
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogHelper.d(TAG, "onUnbind");
        return false;
    }

    @Override
    public void init(Context context) {
        iMqtt = MqttImpl.getInstance();
        iMqtt.init(context);
    }

    @Override
    public void subscribe(String[] topic, int[] qos) {
        iMqtt.subscribe(topic, qos);
    }

    @Override
    public void close() {
        iMqtt.close();
    }

    @Override
    public void disconnect() {
        iMqtt.disconnect();
    }

    @Override
    public boolean isConnected() {
        return iMqtt.isConnected();
    }

    @Override
    public void publish(String jsonMsg, String topic, int qos, boolean retained) {
        iMqtt.publish(jsonMsg, topic, qos, retained);
    }

}

