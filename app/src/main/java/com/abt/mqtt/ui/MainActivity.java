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
import com.abt.mqtt.R;
import com.abt.mqtt.service.MqttService;

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

    @OnClick({R.id.button1, R.id.button2}) void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                LogHelper.d(TAG, "bindService");
                bindService.publish();
                    break;
            case R.id.button2:
                LogHelper.d(TAG, "subscribeTopic");
                bindService.subscribeTopic();
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
        LogHelper.d(TAG, "unbindService");
    }
}
