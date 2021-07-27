package com.yolo.wz.redisdemo.util;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

@Slf4j
public class IpUtil {
    public static String getLinuxLocalIp() {
        String ip = "";

        try {
            for (Enumeration en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = (NetworkInterface) en.nextElement();

                String name = intf.getName();

                if (!name.contains("docker") && !name.contains("lo")) {
                    for (Enumeration enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                        InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();

                        if (!inetAddress.isLoopbackAddress()) {
                            String ipaddress = inetAddress.getHostAddress();

                            if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {
                                ip = ipaddress;

                            }

                        }

                    }

                }

            }

        } catch (SocketException ex) {
            ip = "127.0.0.1";

            log.error("获取ip异常", ex);

        }

        return ip;

    }

}