package com.abt.mqtt.bean.bese;

/**
 * @描述： @Send
 * @作者： @黄卫旗
 * @创建时间： @2018/9/22
 */
public class Send {
    /**
     * cmd : MQTTYPE_USER_IPCAM_START
     * cmd_type : request
     * sessionid : 0
     */
    private String cmd;
    private String cmd_type;
    private int sessionid;

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
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

    public void setSessionid(int sessionid) {
        this.sessionid = sessionid;
    }

}
