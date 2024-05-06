package cn.xwlin.dubbo.client.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPUtil {
    /**
     * 获取本地电脑ip地址
     *
     * @return
     */
    public static String getLocalIpAddress() {
        InetAddress ip;
        String IP = null;
        try {
            ip = Inet4Address.getLocalHost();
            IP = ip.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "";
        }
        return IP;
    }

    public static void main(String[] args) {
        System.out.println(getLocalIpAddress());
    }
}