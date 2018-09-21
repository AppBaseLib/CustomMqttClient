package com.abt.mqtt.bean;

/**
 * @描述： @Util
 * @作者： @黄卫旗
 * @创建时间： @2018/9/22
 */
public class Util {

    public static void main(String[] args) {
        //String s = "{”cmd”:MQTTYPE_USER_IPCAM_RECORD_PLAYCONTROL,”subcmd”:MQTTCTR L_RECORD_PLAY_START,”timestamp”:1531458802,\"cmd_type”:\"request\",”ses sionid”:0}";
        String s = "{\"cmd_type\":\"response\",\"cmd\":MQTTYPE_USER_IPCAM_RECORD_PLAYC ONTROL,”subcmd”:MQTTCTRL_RECORD_PLAY_START,\"result\":1,\"status\": 1,”sessionid”:0}";
        s = s.replaceAll("\\”", "\"");
        System.out.println(s);
    }

}
