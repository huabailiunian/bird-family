package com.bird.core.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author youly
 * 2019/7/9 14:40
 */
public class InetAddressTools {

    private static Logger logger = LoggerFactory.getLogger(InetAddressTools.class);

    public static String getHostAddress() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            return address.getHostAddress();
        } catch (UnknownHostException e) {
            logger.warn("Get localhost address failed, error info: {}", e.getMessage());
        }
        return null;
    }
}
