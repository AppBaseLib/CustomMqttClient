package com.abt.mqtt.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.abt.basic.logger.LogHelper;
import com.abt.basic.util.ToastUtil;
import com.abt.mqtt.R;
import com.abt.mqtt.config.Eventconfig;
import com.abt.mqtt.event.CallbackEvent;
import com.abt.mqtt.event.ConnectEvent;
import com.abt.mqtt.service.MqttService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @描述： @MainActivity
 * @作者： @黄卫旗
 * @创建时间： @2018/9/21
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private MqttService bindService = null;
    private boolean isBound = false;

    @OnClick({R.id.subscribe, R.id.publish}) void onClick(View v) {
        switch (v.getId()) {
            case R.id.subscribe:
                LogHelper.d(TAG, "subscribe topic");
                String[] topics = {"qaiot/mqtt/user/0TdHLPG-RMqWXg7q7sIXEA/"};
                int[] qoss = {1};
                bindService.subscribe(topics, qoss);
                break;
            case R.id.publish:
                LogHelper.d(TAG, "publish btn click");
                String jsonMsg = "{\"type\":hi camera, I am from app's msg!!}";
                String topic = "qaiot/mqtt/0TdHLPG-RMqWXg7q7sIXEA";
                int qos = 1;
                boolean retained = true;
                bindService.publish(jsonMsg, topic, qos, retained);
                break;
        }
    }

    private ServiceConnection mConn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            isBound = true;
            MqttService.MyBinder binder = (MqttService.MyBinder) service;
            bindService = binder.getService();
            LogHelper.d(TAG, "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) { }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogHelper.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        startService();
    }

    private void startService() {
        Intent intent = new Intent(MainActivity.this, MqttService.class);
        this.startService(intent);
        this.bindService(intent, mConn, BIND_AUTO_CREATE);
        LogHelper.d(TAG, "bindService");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unbindService(mConn);
        EventBus.getDefault().unregister(this);
        LogHelper.d(TAG, "unbindService");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onConnectAction(ConnectEvent event) {
        LogHelper.d(TAG, "onConnectAction, status=" + event.getStatus());
        if (event.getStatus()) {
            ToastUtil.show("Connection Success!!");
        } else {
            ToastUtil.show("Connection Failed!!");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMqttCallback(CallbackEvent event) {
        switch (event.getType()) {
            case Eventconfig.MESSAGE_ARRIVED:
                ToastUtil.show("MESSAGE_ARRIVED!!");
                LogHelper.d(TAG, "MESSAGE_ARRIVED=" + event.getMsg());
                break;
            case Eventconfig.DELIVERY_COMPLETE:
                ToastUtil.show("MESSAGE_DELIVERY_COMPLETE!!");
                LogHelper.d(TAG, "MESSAGE_DELIVERY_COMPLETE");
                break;
            case Eventconfig.CONNECTION_LOST:
                ToastUtil.show("MQTT_CONNECTION_LOST!!");
                LogHelper.d(TAG, "MQTT_CONNECTION_LOST");
                break;
        }
    }
}
