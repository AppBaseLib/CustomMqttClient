package com.abt.mqtt.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.abt.basic.BasicApplication;
import com.abt.mqtt.impl.IMqtt;
import com.abt.mqtt.impl.MqttImpl;

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
        iMqtt = MqttImpl.getInstance();
        iMqtt.init(BasicApplication.getAppContext());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
