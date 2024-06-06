package cn.xwlin.configcenter.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class OSUtils {
  private static String localName = null;
  private static String localIp = null;

  public OSUtils() {
  }

  public static String linuxLocalName() {
    if (localName == null) {
      InetAddress localhost = getInetAddr();
      if (localhost != null) {
        localName = localhost.getHostName();
      }
    }

    return localName;
  }

  private static synchronized InetAddress getInetAddr() {
    InetAddress localhost = null;

    try {
      localhost = InetAddress.getLocalHost();
    } catch (UnknownHostException var2) {
      var2.printStackTrace();
    }

    return localhost;
  }

  public static String getLocalIP() {
    if (localIp == null) {
      doGetLocalIp();
    }

    return localIp;
  }

  private static synchronized void doGetLocalIp() {
    InetAddress ip = null;

    try {
      if (isWindowsOS()) {
        ip = InetAddress.getLocalHost();
      } else {
        boolean bFindIP = false;
        Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();

        label42:
        while(true) {
          while(true) {
            if (!netInterfaces.hasMoreElements() || bFindIP) {
              break label42;
            }

            NetworkInterface ni = (NetworkInterface)netInterfaces.nextElement();
            Enumeration<InetAddress> ips = ni.getInetAddresses();

            while(ips.hasMoreElements()) {
              ip = (InetAddress)ips.nextElement();
              if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && !ip.getHostAddress().contains(":")) {
                bFindIP = true;
                break;
              }
            }
          }
        }
      }
    } catch (Exception var5) {
      var5.printStackTrace();
    }

    if (null != ip) {
      localIp = ip.getHostAddress();
    }

  }

  public static boolean isEqualOtherIp(String ip) {
    boolean flag = false;
    if (ip != null && ip.length() != 0) {
      InetAddress localAddress = null;

      try {
        boolean bFindIP = false;
        Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();

        while(true) {
          while(netInterfaces.hasMoreElements() && !bFindIP) {
            NetworkInterface ni = (NetworkInterface)netInterfaces.nextElement();
            Enumeration<InetAddress> ips = ni.getInetAddresses();

            while(ips.hasMoreElements()) {
              localAddress = (InetAddress)ips.nextElement();
              if (ip.equals(localAddress.getHostAddress())) {
                bFindIP = true;
                flag = true;
                break;
              }
            }
          }

          return flag;
        }
      } catch (Exception var7) {
        var7.printStackTrace();
        return flag;
      }
    } else {
      return false;
    }
  }

  public static InetAddress getInetAdress() {
    InetAddress ip = null;

    try {
      if (isWindowsOS()) {
        ip = InetAddress.getLocalHost();
      } else {
        boolean bFindIP = false;
        Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();

        while(true) {
          while(netInterfaces.hasMoreElements() && !bFindIP) {
            NetworkInterface ni = (NetworkInterface)netInterfaces.nextElement();
            Enumeration<InetAddress> ips = ni.getInetAddresses();

            while(ips.hasMoreElements()) {
              ip = (InetAddress)ips.nextElement();
              if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
                bFindIP = true;
                break;
              }
            }
          }

          return ip;
        }
      }
    } catch (Exception var5) {
      var5.printStackTrace();
    }

    return ip;
  }

  public static boolean isWindowsOS() {
    boolean isWindowsOS = false;
    String osName = System.getProperty("os.name");
    if (osName.toLowerCase().indexOf("windows") > -1) {
      isWindowsOS = true;
    }

    return isWindowsOS;
  }
}