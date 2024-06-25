package com.java_ne.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utility {
    public static final Logger logger = LoggerFactory.getLogger(Utility.class);
    public static String toAscii(String email) {
        int atIndex = email.lastIndexOf('@');
        if (atIndex >= 0) {
            String localPart = email.substring(0, atIndex);
            String domain = email.substring(atIndex + 1);
            email = localPart + "@" + java.net.IDN.toASCII(domain);
        }
        return email;
    }
}
