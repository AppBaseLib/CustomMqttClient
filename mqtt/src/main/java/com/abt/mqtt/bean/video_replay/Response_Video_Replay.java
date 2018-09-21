package com.abt.mqtt.bean.video_replay;

/**
 * @描述： @
 * @作者： @黄卫旗
 * @创建时间： @2018/9/22
 */
public class Response_Video_Replay {


    /**
     * cmd_type : response
     * cmd : MQTTYPE_USER_IPCAM_RECORD_PLAYC ONTROL
     * subcmd : MQTTCTRL_RECORD_PLAY_START
     * result : 1
     * status : 1
     * sessionid : 0
     */

    private String cmd_type;
    private String cmd;
    private String subcmd;
    private int result;
    private int status;
    private int sessionid;

    public String getCmd_type() {
        return cmd_type;
    }

    public void setCmd_type(String cmd_type) {
        this.cmd_type = cmd_type;
    }

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

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSessionid() {
        return sessionid;
    }

    public void setSessionid(int sessionid) {
        this.sessionid = sessionid;
    }

}
