package com.abt.mqtt.bean.video_replay;

/**
 * @描述： @Send_Video_Replay
 * @作者： @黄卫旗
 * @创建时间： @2018/9/22
 */
public class Send_Video_Replay {


    /**
     * cmd : MQTTYPE_USER_IPCAM_RECORD_PLAYCONTROL
     * subcmd : MQTTCTR L_RECORD_PLAY_START
     * timestamp : 1531458802
     * cmd_type : request
     * sessionid : 0
     */

    private String cmd;
    private String subcmd;
    private int timestamp;
    private String cmd_type;
    private int sessionid;

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getSubcmd() {
        return subcmd;
    }

    public void setSubcmd(String subcmd) {
        this.subcmd = subcmd;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getCmd_type() {
        return cmd_type;
    }

    public void setCmd_type(String cmd_type) {
        this.cmd_type = cmd_type;
    }

    public int getSessionid() {
        return sessionid;
    }

    public void set_$SesSionid326(int sessionid) {
        this.sessionid = sessionid;
    }
}
