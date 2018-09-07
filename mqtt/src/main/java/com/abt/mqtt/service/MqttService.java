package com.abt.mqtt.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.abt.basic.BaseApplication;
import com.abt.basic.logger.LogHelper;
import com.jooan.mqtt.event.ConnectEvent;
import com.jooan.mqtt.impl.IMqtt;
import com.jooan.mqtt.impl.MqttImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author 黄卫旗
 * @description MqttService
 * @time 2018/09/05
 */
public class MqttService extends Service {

    private static final String TAG = MqttService.class.getSimpleName();
    private IMqtt iMqtt;

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
        iMqtt = MqttImpl.getInstance();
        iMqtt.init(BaseApplication.getAppContext());
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
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogHelper.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        LogHelper.d(TAG, "onDestroy");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onConnectAction(ConnectEvent event) {
        LogHelper.d(TAG, "onConnectAction, status="+event.getStatus());
        if (event.getStatus()) {
            subscribeTopic();
            publish();
        }
    }

    private void subscribeTopic() {
        String[] topic = {"qaiot/mqtt/user/0TdHLPG-RMqWXg7q7sIXEA/"};
        int[] qos = {1};
        iMqtt.subscribe(topic, qos);
    }

    private void publish() {
        String msg = "hi camera, I am from app's msg!!";
        String topic = "qaiot/mqtt/0TdHLPG-RMqWXg7q7sIXEA";
        int qos = 1;
        boolean retained = true;
        iMqtt.publish(msg, topic, qos, retained);
    }
    
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMqttCallback(CallbackEvent event) {
        switch (event.getType()) {
            case Eventconfig.MESSAGE_ARRIVED:
                LogHelper.d(TAG, "MESSAGE_ARRIVED="+event.getMessage());
                break;
            case Eventconfig.DELIVERY_COMPLETE:
                LogHelper.d(TAG, "MESSAGE_DELIVERY_COMPLETE");
                break;
            case Eventconfig.CONNECTION_LOST:
                LogHelper.d(TAG, "MQTT_CONNECTION_LOST");
                break;
        }
    }

}

