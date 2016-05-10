package com.pentair.utils;

import java.io.IOException;

public class ServerRestart {
    private String restartCommandPath;

    public ServerRestart() {
        System.out.println("ServerRestart init.");
    }

    public String getRestartCommandPath() {
        return restartCommandPath;
    }

    public void setRestartCommandPath(String restartCommandPath) {
        this.restartCommandPath = restartCommandPath;
    }

    /*
     * 执行重启服务器的脚本
     */
    public void restart() {
        System.out.println("ServerRestart.restart()");
        try {
            Runtime.getRuntime().exec("cmd /k start " + restartCommandPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
